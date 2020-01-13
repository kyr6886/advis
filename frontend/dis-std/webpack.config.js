var path = require('path')
var webpack = require('webpack')

module.exports = {
  entry: {
    // codeView: './src/codeView-controller.js',
    // main: './src/main-controller.js',
    // search: './src/search-controller.js',
    // disasterDme: './src/disasterDme-controller.js',
    // disasterDmeEarthquake: './src/disasterDme-earthquake-controller.js',
    // disasterDmeRain: './src/disasterDme-rain-controller.js',
    // disasterDmeSnow: './src/disasterDme-snow-controller.js',
    // similarPathTyphoon:'./src/similarPathTyphoon-controller.js',
    // socDme: './src/socDme-controller.js',
    // category: './src/category-controller.js',
    analisys: './src/disasterDme-analisys-controller.js',
    // analisysRain: './src/disasterDme-analisys-rain-controller.js',
    // analisysEarthquake: './src/disasterDme-analisys-earthquake-controller.js',
    // disEventAction: './src/disEventAction-controller.js',
    // analisysAi:'./src/disasterDme-analisys-ai-controller.js',
    // report:'./src/report-controller.js',
    // reportEarthquake:'./src/report-earthquake-controller.js',
    // reportVirusAi:'./src/report-virusAi-controller.js',
    // reportRain:'./src/report-rain-controller.js',
	    
  },
  output: {
    path: path.resolve(__dirname, './../../src/main/webapp/contents/advis/js/model/vue'),
    publicPath: '/vue-controller/',
    filename: '[name]-controller.min.js'
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: [
          'vue-style-loader',
          'css-loader'
        ],
      },      {
        test: /\.vue$/,
        loader: 'vue-loader',
        options: { 
          loaders: {
          }
          // other vue-loader options go here
        }
      },
      {
        test: /\.js$/,
        loader: 'babel-loader',
        exclude: /node_modules/
      },
      {
        test: /\.(png|jpg|gif|svg)$/,
        loader: 'file-loader',
        options: {
          name: '[name].[ext]?[hash]'
        }
      }
    ]
  },
  resolve: {
    alias: {
      'vue$': 'vue/dist/vue.esm.js'
    },
    extensions: ['*', '.js', '.vue', '.json']
  },
  devServer: {
    historyApiFallback: true,
    noInfo: true,
    overlay: true
  },
  performance: {
    hints: false
  },
  devtool: '#inline-source-map'
}

if (process.env.NODE_ENV === 'production') {
  module.exports.devtool = '#inline-source-map'
  // http://vue-loader.vuejs.org/en/workflow/production.html
  module.exports.plugins = (module.exports.plugins || []).concat([
    new webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: '"production"'
      }
    }),
    new webpack.optimize.UglifyJsPlugin({
      sourceMap: true,
      compress: {
        warnings: false
      }
    }),
    new webpack.LoaderOptionsPlugin({
      minimize: true
    })
  ])
}
