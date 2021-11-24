function addIngredient() {
    var $numItems = $('.ingredient').length
    if ($numItems > 5) {
        return;
    }
    var ingredient_copy = $('.ingredient:last').clone();
    $("#add-ingredients").append(ingredient_copy);
    $numItems = $('.ingredient').length
    if ($numItems > 5) {
        $("#add_ingredient_button").addClass("d-none");
    }
}