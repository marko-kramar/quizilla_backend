let QuizillaUtil = function() {};

QuizillaUtil.createRowNumCell = function(index, parentElem) {
    let rowNumCell = $(document.createElement("th")).appendTo(parentElem);
    rowNumCell.attr("scope", "row");
    rowNumCell.text(index);
};

QuizillaUtil.createDataCell = function(value, parentElem) {
    let idCell = $(document.createElement("td")).appendTo(parentElem);
    idCell.text(value);
    return idCell;
};

QuizillaUtil.getClearTableBody = function(tableBodyElem) {
    tableBodyElem.html("");
    return tableBodyElem;
};