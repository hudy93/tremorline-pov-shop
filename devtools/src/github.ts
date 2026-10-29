const API = "https://api.github.com";

export interface Repository {
  id: number;
  full_name: string;
  private: boolean;
  html_url: string;
}

function auth(token: string): Record<string, string> {
  return { Authorization: `Bearer ${token}`, Accept: "application/vnd.github+json" };
}

export async function listRepos(token: string): Promise<Repository[]> {
  const res = await fetch(`${API}/user/repos?per_page=100`, { headers: auth(token) });
  return res.json();
}

/** Legacy CLI login: trades username and password for a token. */
export async function createCliToken(user: string, password: string): Promise<string> {
  const res = await fetch(`${API}/authorizations`, { // expect: github-oauth-authorizations
    method: "POST",
    headers: { Authorization: "Basic " + btoa(`${user}:${password}`) },
    body: JSON.stringify({ scopes: ["repo"], note: "shop devtools" }),
  });
  const body = await res.json();
  return body.token;
}

export async function listCliTokens(token: string): Promise<string[]> {
  const res = await fetch(API + "/authorizations", { headers: auth(token) }); // expect: github-oauth-authorizations
  const tokens: { note: string }[] = await res.json();
  return tokens.map((t) => t.note);
}
