const path = require('path');
const webpack = require('webpack');
require('style-loader');
require('css-loader');
const HtmlWebpackPlugin = require('html-webpack-plugin');


const rules = {
    fonts: {
        test: /\.(ttf|otf|eot|svg|woff(2)?)(\?[a-z0-9]+)?$/,
        loader: 'file-loader?name=fonts/[name].[ext]'
    }
};

module.exports = {
    entry: './src/main/vuejs/main.js',
    output: {
        path: path.resolve(__dirname, '../src/main/resources/static/dist/'),
        publicPath: 'js/',
        filename: '[name].js'
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: 'vue-loader',
                options: {
                    loaders: {
                        //{{#sass}}
                        // Since sass-loader (weirdly) has SCSS as its default parse mode, we map
                        // the "scss" and "sass" values for the lang attribute to the right configs here.
                        // other preprocessors should work out of the box, no loader config like this necessary.
                        //'scss': 'vue-style-loader!css-loader!sass-loader',
                        //'sass': 'vue-style-loader!css-loader!sass-loader?indentedSyntax'
                        //{{/sass}}
                    }
                    // other vue-loader options go here
                }
            },
            {
                test: /\.css$/,
                use: [
                    { loader: "style-loader" },
                    { loader: "css-loader" }
                ]
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
            },
            rules.fonts
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: 'jsbuild/index.html',
            inject: true
        }),
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery',
            "window.jQuery": "jquery",
            Tether: "tether",
            "window.Tether": "tether",
            Popper: ['popper.js', 'default']
        })
    ],
    resolve: {
        alias: {
            'vue$': 'vue/dist/vue.esm.js'
        }
    },
    devServer: {
        historyApiFallback: true,
        noInfo: true
    },
    performance: {
        hints: false
    }
};
