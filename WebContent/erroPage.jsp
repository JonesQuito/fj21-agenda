<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Página de Erro</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css" />
<style>
.cabecalho h3 {
	color: red;
	font-size: 20pt;
	font-weight: 500;
	margin: 0px;
}
.wrapp{
	margin-top: 45px;
}
</style>
</head>

<body>
	<div class="wrapp">
		<header class="cabecalho">
			<h3>ERRO</h3>
		</header>
		<div class="interface">
			<div class="alert alert-success" role="alert"
				style="margin-top: 45px">${erro}</div>

			<a href="${url}">${LinkMessage}</a>
		</div>
	</div>


</body>
</html>