$('input:radio[name="command"]').change(function () {
    if ($(this).val() == 1) {
        $("#tabuleiro").attr("hidden", false);
        $(this).attr("checked", true);

        $("#background").attr("hidden", true);
        $(this).attr("checked", true);

    } else if ($(this).val() == 2) {
        $("#background").attr("hidden", false);
        $(this).attr("checked", true);

        $("#tabuleiro").attr("hidden", true);
        $(this).attr("checked", true);
    }
});