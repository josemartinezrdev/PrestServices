import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      "/auth": {
        target: "http://localhost:9191",
        changeOrigin: true,
        secure: false,
      },
      "/api": {
        target: "http://localhost:9191",
        changeOrigin: true,
        secure: false,
      },
    },
  },
});
