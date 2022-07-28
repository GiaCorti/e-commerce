FROM nginx:1.20.2-alpine

COPY dist/e-commerce-frontend /usr/share/nginx/html

COPY docker-resources/nginx/default.conf /etc/nginx/conf.d/default.conf

EXPOSE 8085