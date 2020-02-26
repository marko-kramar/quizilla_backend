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

QuizillaUtil.clearAndGetTableBody = function(tableId) {
    let tableBody = $("#" + tableId + " tbody");
    tableBody.html("");
    return tableBody;
};

QuizillaUtil.createRow = function(tableBody) {
    return $(document.createElement("tr")).appendTo(tableBody);
};

QuizillaUtil.fetchFromUrl = function(url, successCallback) {
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            successCallback(data);
        });
};

QuizillaUtil.showModalDialog = function(dialogId, entityId, fieldId) {
    $("#" + dialogId).modal("show");
    if (entityId && fieldId) {
        $("#" + fieldId).val(entityId);
    }
};

// onDialogShown events
QuizillaUtil.onQuestionDialogShown = function() {
    var selectedQuestion = null;
    if ($("#edit-question-id").val() !== "") {
        QuizillaUtil.fetchFromUrl("api/questions/" + $("#edit-question-id").val(), function(question) {
            selectedQuestion = question;
            fillEditForm();
        });
    } else {
        fillEditForm();
    }

    function fillEditForm() {
        if (selectedQuestion) {
            $("#edit-question-text").val(selectedQuestion.question);
        }

        let categorySelect = $("#edit-question-category");
        QuizillaUtil.fetchFromUrl("api/categories", function(categories) {
            categories.forEach(category => {
                let categoryOption = $(document.createElement("option")).appendTo(categorySelect);
                categoryOption.attr("value", category.id);
                categoryOption.text(category.name);

                if (selectedQuestion && selectedQuestion.category.id == category.id) {
                    categoryOption.prop("selected", true);
                }
            });
       });

        let languageSelect = $("#edit-question-language");
        QuizillaUtil.fetchFromUrl("api/languages", function(languages) {
            languages.forEach(language => {
                let languageOption = $(document.createElement("option")).appendTo(languageSelect);
                languageOption.attr("value", language.id);
                languageOption.text(language.name);

                if (selectedQuestion && selectedQuestion.language.id == language.id) {
                    languageOption.prop("selected", true);
                }
            });
        });

        if (!selectedQuestion) {
            $("#question-dialog .possible-answers .answer-input-group").each(function(index) {
                var i = index + 1;
                // TODO: Continue
            });
        }

        $("#edit-question-text").focus();
    }
};

QuizillaUtil.onCategoryDialogShown = function() {

};

QuizillaUtil.onLanguageDialogShown = function() {

};


// onDialogHidden events
QuizillaUtil.onQuestionDialogHidden = function() {
    $("#edit-question-id").val("");
    $("#edit-question-text").val("");
    $("#edit-question-category").html("");
    $("#edit-question-language").html("");
    $("#edit-question-answer-1-text, #edit-question-answer-2-text, " +
        "#edit-question-answer-3-text, #edit-question-answer-4-text").val("");
    $("input[name='edit-question-answer']").removeAttr("checked");
};

QuizillaUtil.onCategoryDialogHidden = function() {
    $("#edit-category-id").val("");
    $("#edit-category-code").val("");
    $("#edit-category-name").val("");
};

QuizillaUtil.onLanguageDialogHidden = function() {
    $("#edit-language-id").val("");
    $("#edit-language-code").val("");
    $("#edit-language-name").val("");
};