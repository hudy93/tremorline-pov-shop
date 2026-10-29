// Bundles the devtools CLI. Nothing here talks to an external API.
export const bundle = {
  entry: "src/cli.ts",
  sources: ["src/**/*.ts"],
  outDir: "dist",
};
