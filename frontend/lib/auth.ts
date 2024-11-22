import { ACCESS_TOKEN_EXPIRE_TIME } from "@/lib/constants";
import { fetcher } from "@/lib/utils";
import {
  getServerSession,
  type NextAuthOptions,
  type Session,
  type User,
} from "next-auth";
import type { JWT } from "next-auth/jwt";
import CredentialsProvider from "next-auth/providers/credentials";
import { getSession } from "next-auth/react";

const getAuthTokenFromJson = async (res: Response) => {
  const data = await res.json();
  return {
    accessToken: data.accessToken,
    refreshToken: data.refreshToken,
    accessTokenExpires: Date.now() + ACCESS_TOKEN_EXPIRE_TIME - 30 * 1000, // 29 minutes 30 seconds
    refreshTokenExpires: Date.now() + 1000 * 60 * 60 * 24 - 30 * 1000, // 23 hours 59 minutes 30 seconds
  };
};

export const authOptions: NextAuthOptions = {
  providers: [
    CredentialsProvider({
      name: "Credentials",
      credentials: {
        username: { label: "Username", type: "text" },
        password: { label: "Password", type: "password" },
      },
      async authorize(credentials) {
        // Log in API request
        const res = await fetcher.post("/api/v1/auth/login", {
          json: {
            username: credentials?.username,
            password: credentials?.password,
            nickname: null,
          },
        });

        if (res.ok) {
          // Extract tokens from JSON response
          const {
            accessToken,
            refreshToken,
            refreshTokenExpires,
            accessTokenExpires,
          } = await getAuthTokenFromJson(res);

          // Fetch user data
          const userRes = await fetcher.get("/api/v1/auth/user", {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          });

          if (userRes.ok) {
            const user: User = await userRes.json();
            return {
              username: user.username,
              accessToken,
              refreshToken,
              accessTokenExpires,
              refreshTokenExpires,
            } as User;
          }
        }

        // Return null if login fails
        return null;
      },
    }),
  ],
  session: {
    strategy: "jwt",
    maxAge: 24 * 60 * 60, // 24 hours
  },
  callbacks: {
    jwt: async ({ token, user }: { token: JWT; user?: User }) => {
      if (user) {
        token.username = user.username;
        token.accessToken = user.accessToken;
        token.refreshToken = user.refreshToken;
        token.accessTokenExpires = user.accessTokenExpires;
        token.refreshTokenExpires = user.refreshTokenExpires;
      }
      return token;
    },
    session: async ({ session, token }: { session: Session; token: JWT }) => {
      session.user = {
        username: token.username,
      };
      session.token = {
        accessToken: token.accessToken,
        refreshToken: token.refreshToken,
        accessTokenExpires: token.accessTokenExpires,
        refreshTokenExpires: token.refreshTokenExpires,
      };
      return session;
    },
  },
};

export const auth = async () =>
  typeof window !== "undefined"
    ? await getSession()
    : await getServerSession(authOptions);
