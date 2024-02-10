FROM node:lts as builder
WORKDIR /app
COPY . .
RUN npm i
RUN npm run build

FROM node:lts
WORKDIR /app
COPY --from=builder /app/dist ./dist
