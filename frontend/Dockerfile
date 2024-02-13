FROM node:lts
RUN apt update && apt upgrade -y
RUN apt install npm
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build
