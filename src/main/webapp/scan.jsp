<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.net.URLEncoder" %>
<%
    String ctx = request.getContextPath();
%>
<!doctype html>
<html>
<head>
  <meta charset="utf-8"/>
  <title>Scanner un code-barres</title>
  <style>
    #video { width: 100%; max-width: 600px; border: 1px solid #ccc; }
    #result { margin-top: 1em; font-weight: bold; }
    button { margin-top: .5em; }
  </style>
</head>
<body>
  <h2>Scanner un code-barres / QR</h2>
  <video id="video" autoplay playsinline></video>
  <div id="result">En attente de lecture…</div>
  <div>
    <button id="start">Démarrer la caméra</button>
    <button id="stop">Arrêter</button>
  </div>

  <!-- ZXing JS (browser build) depuis CDN -->
  <script type="module">
    import { BrowserMultiFormatReader } from "https://unpkg.com/@zxing/browser@0.0.12/esm/index.js";

    const videoElem = document.getElementById('video');
    const resultDiv = document.getElementById('result');
    const startBtn = document.getElementById('start');
    const stopBtn = document.getElementById('stop');

    const codeReader = new BrowserMultiFormatReader();
    let selectedDeviceId = null;
    let controls = null;

    async function startScanner() {
      try {
        // Récupère les devices et choisis la caméra arrière si possible
        const devices = await BrowserMultiFormatReader.listVideoInputDevices();
        if (devices.length === 0) {
          resultDiv.textContent = "Aucune caméra trouvée.";
          return;
        }
        // Choisir une caméra (si possible la dernière qui est souvent arrière sur mobile)
        selectedDeviceId = devices[devices.length - 1].deviceId;

        controls = await codeReader.decodeFromVideoDevice(selectedDeviceId, videoElem, (result, err) => {
          if (result) {
            const text = result.getText();
            resultDiv.textContent = "Code détecté: " + text;
            // Stopper le scanner avant redirection
            stopScanner();
            // Rediriger vers le servlet de recherche d'article
            const ctx = "<%= ctx %>";
            // Encodage pour URL
            const encoded = encodeURIComponent(text);
            window.location.href = ctx + "/servlet/FindArticle?code=" + encoded;
          }
          if (err && !(err.name === 'NotFoundException')) {
            console.log(err);
          }
        });
      } catch (e) {
        console.error(e);
        resultDiv.textContent = "Erreur lors de l'accès à la caméra: " + e;
      }
    }

    function stopScanner() {
      if (controls && typeof controls.stop === 'function') {
        controls.stop();
        controls = null;
      }
      // Arrêter piste média si active
      const stream = videoElem.srcObject;
      if (stream) {
        stream.getTracks().forEach(t => t.stop());
        videoElem.srcObject = null;
      }
    }

    startBtn.addEventListener('click', startScanner);
    stopBtn.addEventListener('click', stopScanner);

    // Optionnel : démarrer automatiquement sur page load
    // startScanner();
  </script>
</body>
</html>
