module.exports = {
  extends: [
    'standard',
    'plugin:vue/recommended' // or 'plugin:vue/base'
  ],
  rules: {
    // allow console and debugger in development
    "no-console": process.env.NODE_ENV === "production" ? 2 : 0,
    "no-debugger": process.env.NODE_ENV === "production" ? 2 : 0
  }
}
