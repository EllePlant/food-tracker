import { API_URL } from "./GlobalStates";

export default function LoginUser(email, password, setError, updateData, navigate) {
  const url = `${API_URL}/user/login`;

  const refreshBearerToken = (refreshToken) => {
    const refreshUrl = `${API_URL}/user/refresh`;

    return fetch(refreshUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ refreshToken:refreshToken }),
    }).then((res) =>
    res.json().then((res) => {
     if(res.error){
       setError(res)
     } else {
      updateData({
        email: email,
        bearerToken: res.bearerToken.token,
        refreshToken: res.refreshToken.token
      });
      const bearerTokenExp = Date.now() / 1000 + res.bearerToken.expires_in;
      const refreshThreshold = res.bearerToken.expires_in/2;
      const refreshTimeout = bearerTokenExp - refreshThreshold - Date.now() / 1000;
      setTimeout(() => {
        refreshBearerToken(res.refreshToken.token)
      }, refreshTimeout * 1000);
    }}));
  };



  return fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ email: email, password: password}),
  })
  .then((res) =>
   res.json().then((res) => {
      if(res.error){
        setError(res)
      } else {
      updateData({ 
        email: email,
        bearerToken: res.bearerToken.token,
        refreshToken: res.refreshToken.token 
      });
      navigate(-1);
      const bearerTokenExp = Date.now() / 1000 + res.bearerToken.expires_in;
      const refreshThreshold = res.bearerToken.expires_in/2;
      const refreshTimeout = bearerTokenExp - refreshThreshold - Date.now() / 1000;
      setTimeout(() => {
        refreshBearerToken(res.refreshToken.token)
      }, refreshTimeout * 1000);
    }})
    );
}



