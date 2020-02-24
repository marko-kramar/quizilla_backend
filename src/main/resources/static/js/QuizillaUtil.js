let QuizillaUtil = function() {};

QuizillaUtil.createCheckboxCell = function (index, parentElem){
    let checkboxCell = $(document.createElement("td")).appendTo(parentElem);
    checkboxCell.addClass("checkbox-col");

    let customControlElem = $(document.createElement("div")).appendTo(checkboxCell);
    customControlElem.addClass("custom-control custom-checkbox");

    let inputCheckbox = $(document.createElement("input")).appendTo(customControlElem);
    inputCheckbox.attr("type", "checkbox");
    inputCheckbox.addClass("form-check-input");
    inputCheckbox.data("for-row", index);
}

QuizillaUtil.createRowNumCell = function(index, parentElem) {
    let rowNumCell = $(document.createElement("th")).appendTo(parentElem);
    rowNumCell.attr("scope", "row");
    rowNumCell.text(index);
};

QuizillaUtil.createDataCell = function(value, parentElem, hidden) {
    let idCell = $(document.createElement("td")).appendTo(parentElem);
    idCell.text(value);
    if (hidden) {
        idCell.css("display", "none");
    }
    return idCell;
};

QuizillaUtil.createActionsCell = function(parentElem, id) {
    let actionsCell = $(document.createElement("td")).appendTo(parentElem);
    actionsCell.addClass("actions-cell");
    actionsCell.attr("align", "center");

    let editActionIcon = $(document.createElement("i")).appendTo(actionsCell);
    editActionIcon.addClass("edit-action text-warning fas fa-pen");
    editActionIcon.data("id-to-edit", id);
    editActionIcon.attr({
        "title" : "Edit",
        "data-toggle" : "tooltip",
        "data-placement" : "top"
    });

    actionsCell.append("&nbsp;&nbsp;&nbsp;");

    let deleteActionIcon = $(document.createElement("i")).appendTo(actionsCell);
    deleteActionIcon.addClass("delete-action text-danger fas fa-trash");
    deleteActionIcon.data("id-to-delete", id);
    deleteActionIcon.attr({
        "title" : "Delete",
        "data-toggle" : "tooltip",
        "data-placement" : "top"
    });
};

QuizillaUtil.getClearTableBody = function(tableBodyElem) {
    tableBodyElem.html("");
    return tableBodyElem;
};