import { encode, getToken } from "next-auth/jwt";
import { NextResponse, type NextRequest } from "next/server";
import { baseUrl } from "./lib/constants";

const getAuthTokenFromResponse = async (res: Response) => {
  const data = await res.json();
  return {
    accessToken: data.accessToken,
    refreshToken: data.refreshToken,
    accessTokenExpires: Date.now() + 30 * 60 * 1000 - 30 * 1000, // 30 minutes - 30 seconds
    refreshTokenExpires: Date.now() + 7 * 24 * 60 * 60 * 1000 - 30 * 1000, // 7 days - 30 seconds
  };
};

const sessionCookieName = process.env.NEXTAUTH_URL?.startsWith("https://")
  ? "__Secure-next-auth.session-token"
  : "next-auth.session-token";

export const middleware = async (req: NextRequest) => {
  const token = await getToken({ req, secret: process.env.NEXTAUTH_SECRET });
  console.log("token", token);

  // Reissue access token if expired
  if (token && token.accessTokenExpires <= Date.now()) {
    const reissueRes = await fetch(baseUrl + "/api/v1/auth/reissue", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        accessToken: token.accessToken,
        refreshToken: token.refreshToken,
      }),
      cache: "no-store",
    });

    if (reissueRes.ok) {
      // Update session token with new tokens
      const {
        accessToken,
        refreshToken,
        accessTokenExpires,
        refreshTokenExpires,
      } = await getAuthTokenFromResponse(reissueRes);

      const newToken = await encode({
        secret: process.env.NEXTAUTH_SECRET as string,
        token: {
          ...token,
          accessToken,
          refreshToken,
          accessTokenExpires,
          refreshTokenExpires,
        },
        maxAge: 7 * 24 * 60 * 60, // 7 days
      });

      req.cookies.set(sessionCookieName, newToken);
      const res = NextResponse.next({
        request: {
          headers: req.headers,
        },
      });
      res.cookies.set(sessionCookieName, newToken, {
        maxAge: 7 * 24 * 60 * 60,
        httpOnly: true,
        sameSite: "lax",
      });
      return res;
    } else if (reissueRes.status === 401) {
      // If reissue fails, clear the session token
      req.cookies.delete(sessionCookieName);
      const res = NextResponse.next({
        request: {
          headers: req.headers,
        },
      });
      res.cookies.delete(sessionCookieName);
      return res;
    }
  }

  // Continue request if no issues
  return NextResponse.next({
    request: {
      headers: req.headers,
    },
  });
};
