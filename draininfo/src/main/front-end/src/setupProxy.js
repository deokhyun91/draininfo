const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app){
app.use(
createProxyMiddleware({
target: 'http://13.125.176.72:8080',
changeOrigin: true,
pathFilter : '/api', 
}),
);
};