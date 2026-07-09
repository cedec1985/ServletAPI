<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Générateur de codes</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <!--  <link rel="stylesheet" href="style.css"> -->
</head>
<body class="bg-light">

<h1 style="text-align:center">Générer un code-barres ou un QR Code</h1>

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-body">

<form action="Barcode" method="get" class="row g-3">
    <div class="col-md-6">
    <label class="form-label">Type de code</label>
    <input type="checkbox" name="codeTypes" value="ean"> EAN‑13
    <input type="checkbox" name="codeTypes" value="qr"> QR Code
    <input type="checkbox" name="codeTypes" value="code128"> CODE128
    <input type="checkbox" name="codeTypes" value="gs1-128"> GS1‑128
    <input type="checkbox" name="codeTypes" value="itf-14">ITF-14
    <br><br>
    </div>

    <div class="col-md-6">
    <label class="form-label">Type de code</label>
    <select class="form-select" name="codeType">
    <option value="barcode">Code-barres</option>
    <option value="qrcode">QR Code</option>
    </select>
    </div>
    <div class="col-md-6">
    <label class="form-label">code</label>
    <select name="type" id="code-select">
    <option value="">--Veuillez choisir une option--</option>
    <option value="ean-13">EAN-13</option>
    <option value="datamatrix">DATAMATRIX</option>
    <option value="code128">CODE128</option>
    <option value="gs1-128">GS1-128</option>
    <option value="itf-14">ITF-14</option>
    </select>
    <br><br>
    </div>
    
     
   
    <div class="col-md-6">
    <label class="form-label">Libellé produit :</label>
    <input type="text" class="form-control" name="code" required>
    <br><br>
    </div>

    <div class="col-md-6">
    <label class="form-label">Code produit :</label>
    <input type="text" class="form-control" name="msg"  required>
    <br><br>
    </div>

    <div class="col-md-6">
    <label class="form-label">Format :</label>
    <select name="fmt" class="form-select" >
        <option value="png">PNG</option>
        <option value="jpeg">JPEG</option>
        <option value="svg">SVG</option>
    </select>
   <br><br>
   </div>

    <div class="col-md-6">
    <label class="form-label">Marge blanche (mm) :</label>
    <input type="number" class="form-control" name="qz" value="1">
    <br><br>
    </div>

    <div class="col-md-6">
    <label class="form-label">Hauteur (mm):</label>
    <input type="number" class="form-control" name="height" value="50">
    <br><br>
    </div>
    <!--
    <div class="col-md-6">
    <label class="form-label">Type de code-barres :</label>
        <option value="codabar">Codabar</option>
        <option value="code128">CODE128</option>
        <option value="datamatrix">DATAMATRIX</option>
        <option value="code39">CODE39</option>
        <option value="ean-13">EAN-13</option>
        <option value="itf-14">ITF-14</option>
        <option value="UPC-A">UPC-A</option>
    </select>
    <br><br>
    </div>
    -->
    <div class="col-md-6">
    <label class="form-label">Rapport large/étroit :</label>
    <select name="wf" class="form-select">
        <option value="0.2">0.2</option>
        <option value="0.5">0.5</option>
        <option value="1">1</option>
        <option value="2">2</option>
    </select>
    <br><br>
    </div>
    
    <div class="col-md-6">
    <label class="form-label">Largeur du module (barre fine) (mm):</label>
    <select name="mw" class="form-select">
    <option value="0.7">0.7</option>
    <option value="1.3">1.3</option>
    <option value="4.0">4</option>
    <option value="7.0">7</option>
    </select>
    <br><br>
    </div>
    
    <div class="col-md-6">
    <label class="form-label">Résolution</label>
    <select name="dpi" class="form-select">
        <option value="150">150</option>
        <option value="300">300</option>
    </select>
    <br><br>
    </div>
    
    <div class="col-md-6">
    <label class="form-label">Position du texte lisible :</label>
    <select name="human-readable-pos" class="form-select">
        <option value="bottom">Bas</option>
        <option value="top">Haut</option>
        <option value="none">Aucun</option>
    </select>
    <br><br>
    </div>
    
    <div class="col-md-6">
    <label class="form-label">Taille du texte lisible :</label>
    <select name="human-readable-size" class="form-select">
        <option value="2.0">2.0</option>
        <option value="5.0">5.0</option>
        <option value="10.0">10.0</option>
    </select>
    <br><br>
    </div>
    
    <div class="col-md-6">
    <label class="form-label">Police du texte lisible :</label>
    <select name="human-readable-font" class="form-select">
        <option value="Arial">Arial</option>
        <option value="Courier">Courier</option>
        <option value="Helvetica">Helvetica</option>
    </select>
    <br><br>
    </div>
    
    <div class="col-md-6">
    <label class="form-label">Pattern (optionnel)</label>
    <input type="text" class="form-control" name="human-readable-pattern" placeholder="# ### ### ### ###">
    </div>

    <!-- Options QR -->
    <div class="col-md-4">
         <label class="form-label">Taille QR (px)</label>
         <input type="number" class="form-control" name="size" value="350">
         <br><br>
    </div>
     
    <div class="col-12">
    <button type="submit" class="btn btn-primary w-100">Valider</button>
    </div>
           </div>    
     </div>
</div>
</form>
</body>
</html>