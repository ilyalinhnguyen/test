FROM node:alpine3.23 AS deps

WORKDIR /app
RUN npm install -g pnpm
COPY package.json pnpm-lock.yaml* ./
RUN pnpm install --frozen-lockfile=false

FROM node:alpine3.23 AS builder

WORKDIR /app
RUN npm install -g pnpm
COPY --from=deps /app/node_modules ./node_modules
COPY . .
RUN pnpm run build && pnpm prune --prod

FROM node:alpine3.23

ENV NODE_ENV=production
WORKDIR /app
RUN npm install -g pnpm

COPY --from=builder /app /app

EXPOSE 3000

HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD wget -qO- http://127.0.0.1:3000/ >/dev/null || exit 1

USER node
CMD ["pnpm", "run", "start"]
