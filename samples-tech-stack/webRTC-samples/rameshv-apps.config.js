// ecosystem.config.js

module.exports = {  
    apps: [
      {
        name: 'rameshv',
        document_root: 'home/site/wwwroot/rameshv',
        document: 'index.html',
        instances: 1,
        autorestart: true,
        watch: false,
        max_memory_restart: '1G',
        env: {
          NODE_ENV: 'production',
          PORT: 80 
        }
      },
      {
        name: 'simple-chat-app',
        document_root: 'home/site/wwwroot/webrtc',
        document: 'index.html',
        instances: 1,
        autorestart: true,
        watch: false,
        max_memory_restart: '1G',
        env: {
          NODE_ENV: 'production',
          PORT: 4200 
        }
      }
    ]
  };
  