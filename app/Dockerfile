FROM node:12.16.1-alpine3.11 AS cache

WORKDIR /usr/app

COPY package.json /usr/app/package.json
RUN npm install

RUN  rm package.json

CMD ["tail", "-f", "/dev/null"]


FROM node:12.16.1-alpine3.11 AS builder
COPY --from=cache /usr/app/node_modules /usr/app/node_modules

WORKDIR /usr/app
COPY src src
COPY public public
COPY package.json .prettierrc .eslintrc .babelrc ./

RUN npm run build

RUN  rm -rf /usr/app/src && rm -rf /usr/app/public \
  && rm package.json .prettierrc .eslintrc .babelrc
EXPOSE 3000

FROM nginx:1.16.1-alpine

ARG PROFILE

COPY --from=builder /usr/app/build /usr/share/nginx/html
COPY nginx/nginx-$PROFILE.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
EXPOSE 443
ENTRYPOINT ["nginx", "-g", "daemon off;"]

HEALTHCHECK --interval=30s --timeout=3s CMD curl -f http://localhost/health-check || exit 1