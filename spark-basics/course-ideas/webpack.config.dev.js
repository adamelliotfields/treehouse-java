const path = require('path');
const webpack = require('webpack');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = {
  entry: [path.join(__dirname, 'src', 'main', 'webapp', 'index.js')],
  output: {
    filename: 'js/bundle.js',
    path: path.join(__dirname, 'src', 'main', 'resources', 'public')
  },
  devtool: 'eval',
  module: {
    rules: [
      {
        test: /\.jsx?$/,
        exclude: /node_modules/,
        loader: 'babel-loader',
        options: {
          presets: [
            'react-latest'
          ]
        }
      }
    ]
  },
  plugins: [
    new CleanWebpackPlugin([
      path.join(__dirname, 'src', 'main', 'resources', 'public')
    ]),
    new webpack.DefinePlugin({
      'process.env.NODE_ENV': JSON.stringify('development')
    }),
    new CopyWebpackPlugin([
      { from: 'src/main/webapp/assets' }
    ])
  ]
};
