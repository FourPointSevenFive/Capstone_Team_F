if(!self.define){let e,n={};const s=(s,a)=>(s=new URL(s+".js",a).href,n[s]||new Promise((n=>{if("document"in self){const e=document.createElement("script");e.src=s,e.onload=n,document.head.appendChild(e)}else e=s,importScripts(s),n()})).then((()=>{let e=n[s];if(!e)throw new Error(`Module ${s} didn’t register its module`);return e})));self.define=(a,i)=>{const t=e||("document"in self?document.currentScript.src:"")||location.href;if(n[t])return;let c={};const o=e=>s(e,t),r={module:{uri:t},exports:c,require:o};n[t]=Promise.all(a.map((e=>r[e]||o(e)))).then((e=>(i(...e),c)))}}define(["./workbox-1bb06f5e"],(function(e){"use strict";importScripts(),self.skipWaiting(),e.clientsClaim(),e.precacheAndRoute([{url:"/_next/app-build-manifest.json",revision:"5be9557fa352596a35929f9e67a9ce47"},{url:"/_next/static/chunks/117-a3549dc9e58ac562.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/878-7b4d6e36d5d003f0.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/app/_not-found/page-eaa53ae320539b27.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/app/layout-9ca7f6b6af610bd7.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/app/map/page-bade57016e553038.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/app/my/page-751421e80c36807f.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/app/page-3f772dbf7eb14983.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/fd9d1056-aa94ea5c2eabf904.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/framework-f66176bb897dc684.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/main-app-79a4e10ed73b003d.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/main-edc1e5fb9ad5dea9.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/pages/_app-72b849fbd24ac258.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/pages/_error-7ba65e1336b92748.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/chunks/polyfills-42372ed130431b0a.js",revision:"846118c33b2c0e922d7b3a7676f81f6f"},{url:"/_next/static/chunks/webpack-0dae0f0b0d2941b8.js",revision:"gu_SUMw7gnEjn0XQpP_zD"},{url:"/_next/static/css/180c59dc5b484dde.css",revision:"180c59dc5b484dde"},{url:"/_next/static/gu_SUMw7gnEjn0XQpP_zD/_buildManifest.js",revision:"c155cce658e53418dec34664328b51ac"},{url:"/_next/static/gu_SUMw7gnEjn0XQpP_zD/_ssgManifest.js",revision:"b6652df95db52feb4daf4eca35380933"},{url:"/_next/static/media/4473ecc91f70f139-s.p.woff",revision:"78e6fc13ea317b55ab0bd6dc4849c110"},{url:"/_next/static/media/463dafcda517f24f-s.p.woff",revision:"cbeb6d2d96eaa268b4b5beb0b46d9632"},{url:"/favicon/apple-touch-icon.png",revision:"a304d590a880f6ad35768e0b389371b1"},{url:"/favicon/favicon-48x48.png",revision:"5054f1233abc8b2b1af5abcc10c43716"},{url:"/favicon/favicon.ico",revision:"972f3ff71dd3859d270c2b2b58ab57b4"},{url:"/favicon/favicon.svg",revision:"9e5e9b2a838bfaae00d6fb342a1802a2"},{url:"/favicon/site.webmanifest",revision:"f05b2349d77cc5ecc300d8dd627dfd09"},{url:"/favicon/web-app-manifest-192x192.png",revision:"b987f8e87ca9c9eaaf3a8bd034348385"},{url:"/favicon/web-app-manifest-512x512.png",revision:"5433a1f7ec7cb292887e403a663040b1"},{url:"/icons/icon-72.png",revision:"2789611822d6b5771e96477ad81fec18"},{url:"/icons/icon.png",revision:"2f62277fa08fd866532dc27d8b5d6537"}],{ignoreURLParametersMatching:[]}),e.cleanupOutdatedCaches(),e.registerRoute("/",new e.NetworkFirst({cacheName:"start-url",plugins:[{cacheWillUpdate:async({request:e,response:n,event:s,state:a})=>n&&"opaqueredirect"===n.type?new Response(n.body,{status:200,statusText:"OK",headers:n.headers}):n}]}),"GET"),e.registerRoute(/^https:\/\/fonts\.(?:gstatic)\.com\/.*/i,new e.CacheFirst({cacheName:"google-fonts-webfonts",plugins:[new e.ExpirationPlugin({maxEntries:4,maxAgeSeconds:31536e3})]}),"GET"),e.registerRoute(/^https:\/\/fonts\.(?:googleapis)\.com\/.*/i,new e.StaleWhileRevalidate({cacheName:"google-fonts-stylesheets",plugins:[new e.ExpirationPlugin({maxEntries:4,maxAgeSeconds:604800})]}),"GET"),e.registerRoute(/\.(?:eot|otf|ttc|ttf|woff|woff2|font.css)$/i,new e.StaleWhileRevalidate({cacheName:"static-font-assets",plugins:[new e.ExpirationPlugin({maxEntries:4,maxAgeSeconds:604800})]}),"GET"),e.registerRoute(/\.(?:jpg|jpeg|gif|png|svg|ico|webp)$/i,new e.StaleWhileRevalidate({cacheName:"static-image-assets",plugins:[new e.ExpirationPlugin({maxEntries:64,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\/_next\/image\?url=.+$/i,new e.StaleWhileRevalidate({cacheName:"next-image",plugins:[new e.ExpirationPlugin({maxEntries:64,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\.(?:mp3|wav|ogg)$/i,new e.CacheFirst({cacheName:"static-audio-assets",plugins:[new e.RangeRequestsPlugin,new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\.(?:mp4)$/i,new e.CacheFirst({cacheName:"static-video-assets",plugins:[new e.RangeRequestsPlugin,new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\.(?:js)$/i,new e.StaleWhileRevalidate({cacheName:"static-js-assets",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\.(?:css|less)$/i,new e.StaleWhileRevalidate({cacheName:"static-style-assets",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\/_next\/data\/.+\/.+\.json$/i,new e.StaleWhileRevalidate({cacheName:"next-data",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\.(?:json|xml|csv)$/i,new e.NetworkFirst({cacheName:"static-data-assets",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute((({url:e})=>{if(!(self.origin===e.origin))return!1;const n=e.pathname;return!n.startsWith("/api/auth/")&&!!n.startsWith("/api/")}),new e.NetworkFirst({cacheName:"apis",networkTimeoutSeconds:10,plugins:[new e.ExpirationPlugin({maxEntries:16,maxAgeSeconds:86400})]}),"GET"),e.registerRoute((({url:e})=>{if(!(self.origin===e.origin))return!1;return!e.pathname.startsWith("/api/")}),new e.NetworkFirst({cacheName:"others",networkTimeoutSeconds:10,plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute((({url:e})=>!(self.origin===e.origin)),new e.NetworkFirst({cacheName:"cross-origin",networkTimeoutSeconds:10,plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:3600})]}),"GET")}));
