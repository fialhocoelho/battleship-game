function valorMatriz() {
	var indiceHor = document.formul.horizontal.selectedIndex;
	var valorHor = document.formul.horizontal.options[indiceHor].value;

	var indiceVer = document.formul.vertical.selectedIndex;
	var valorVer = document.formul.vertical.options[indiceVer].value;

	var montarTabuleiro = $(function () {
		function MontarTabuleiro() {
			var i;
			$("#tabuleiro").empty();
			for (i = 0; i < valorVer; i++) {
				$("#tabuleiro").append("<div id='linha_" + i.toString() + "' class='linha' >");

				for (j = 0; j < valorHor; j++) {
					var nome_casa = "casa_" + i.toString() + "_" + j.toString();
					var classe = (i % 2 == 0 ? (j % 2 == 0 ? "casa_branca" : "casa_preta") : (j % 2 != 0 ? "casa_branca" : "casa_preta"));
					$("#linha_" + i.toString()).append("<div id='" + nome_casa + "' class='casa " + classe + "' />");
				}
			}
		}
		MontarTabuleiro();
	});
}

