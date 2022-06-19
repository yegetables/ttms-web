module.exports = {
  transpileDependencies: true,
  //打包配置文件
  assetsDir: 'static',
  parallel: false,
  publicPath: './',

  // devServer: {
  //   proxy: {// API代理
  //     '/api': {
  //       // 凡是到/api的请求都会发往下面的地址
  //       target: 'http://192.168.24.86:8080',
  //       changeOrigin: true, // 允许跨域
  //       pathRewrite: {
  //         '^/api': ''
  //       }
  //     }

  //   }
  // }
}
