import withPWA from "next-pwa";

/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true, // Enable React strict mode for improved error handling
  swcMinify: true, // Enable SWC minification for improved performance
  compiler: {
    removeConsole: process.env.NODE_ENV !== "development", // Remove console.log in production
  },
  images: {
    domains: ["hallyugo-s3.s3.ap-northeast-2.amazonaws.com"], // Allowed domains for images
  },
};

export default withPWA({
  dest: "public", // Destination directory for the PWA files
  disable: process.env.NODE_ENV === "development", // Disable PWA in the development environment
  register: true, // Register the PWA service worker
  skipWaiting: true, // Skip waiting for service worker activation
})(nextConfig);
