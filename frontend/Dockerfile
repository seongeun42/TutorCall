FROM node:lts
RUN "apt update && apt upgrade -y"
RUN "apt install npm -y"
WORKDIR /app
COPY . .
RUN "npm i"
RUN "npm run build"
