let QuizillaUtil = function() {};

QuizillaUtil.loadQuestionsTableWithData = function() {
    let tableBody = QuizillaUtil.clearAndGetTableBody("questions-table");

    QuizillaRest.fetchFromUrl("api/questions", function(questions) {
        questions.forEach((question, i) => {
            let rowElem = QuizillaUtil.createRow(tableBody);
            QuizillaUtil.createCheckboxCell(i+1, rowElem);
            QuizillaUtil.createRowNumCell(i+1, rowElem);
            QuizillaUtil.createDataCell(question.id, rowElem, true);
            QuizillaUtil.createDataCell(question.question, rowElem);
            QuizillaUtil.createDataCell(question.category.name, rowElem);
            QuizillaUtil.createDataCell(question.language.name, rowElem);
            QuizillaUtil.createDataCell(question.answers[question.correctAnswerId], rowElem);
            QuizillaUtil.createActionsCell(rowElem, question.id);
        });

        $("[data-toggle='tooltip']").tooltip();
    });
};

QuizillaUtil.loadCategoriesTableWithData = function() {
    let tableBody = QuizillaUtil.clearAndGetTableBody("categories-table");

    QuizillaRest.fetchFromUrl("api/categories", function(categories) {
        categories.forEach((category, i) => {
            let rowElem = QuizillaUtil.createRow(tableBody);
            QuizillaUtil.createCheckboxCell(i+1, rowElem);
            QuizillaUtil.createRowNumCell(i+1, rowElem);
            QuizillaUtil.createDataCell(category.id, rowElem, true);
            QuizillaUtil.createDataCell(category.code, rowElem);
            QuizillaUtil.createDataCell(category.name, rowElem);
            QuizillaUtil.createActionsCell(rowElem, category.id);
        });

        $("[data-toggle='tooltip']").tooltip();
    });
};

QuizillaUtil.loadLanguagesTableWithData = function() {
    let tableBody = QuizillaUtil.clearAndGetTableBody("languages-table");

    QuizillaRest.fetchFromUrl("api/languages", function(languages) {
        languages.forEach((language, i) => {
            let rowElem = QuizillaUtil.createRow(tableBody);
            QuizillaUtil.createCheckboxCell(i+1, rowElem);
            QuizillaUtil.createRowNumCell(i+1, rowElem);
            QuizillaUtil.createDataCell(language.id, rowElem, true);
            QuizillaUtil.createDataCell(language.code, rowElem);
            QuizillaUtil.createDataCell(language.name, rowElem);
            QuizillaUtil.createActionsCell(rowElem, language.id);
        });

        $("[data-toggle='tooltip']").tooltip();
    });
};

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
        QuizillaRest.fetchFromUrl("api/questions/" + $("#edit-question-id").val(), function(question) {
            selectedQuestion = question;
            fillQuestionEditForm();
        });
    } else {
        fillQuestionEditForm();
    }

    function fillQuestionEditForm() {
        if (selectedQuestion) {
            $("#edit-question-text").val(selectedQuestion.question);
        }

        let categorySelect = $("#edit-question-category");
        QuizillaRest.fetchFromUrl("api/categories", function(categories) {
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
        QuizillaRest.fetchFromUrl("api/languages", function(languages) {
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
            $("#question-dialog .answer-input-group").each(function(index) {
                let i = index + 1;
                $(this).data("for-answer-id", i);
                if (i == 1) {
                    $(this).find(".edit-question-answer-radio").prop("checked", true);
                }
            });
        } else {
            let i=0;
            for (let answerId in selectedQuestion.answers) {
                let answer = selectedQuestion.answers[answerId];
                let answerInputGroup = $("#question-dialog .answer-input-group:eq('" + i + "')");
                answerInputGroup.find(".edit-question-answer-text").val(answer);
                answerInputGroup.data("for-answer-id", answerId);
                if (parseInt(answerId) === selectedQuestion.correctAnswerId) {
                    answerInputGroup.find(".edit-question-answer-radio").prop("checked", true);
                }
                i++;
            }
        }

        $("#edit-question-text").focus();
    }
};

QuizillaUtil.onCategoryDialogShown = function() {
    var selectedCategory = null;
    if ($("#edit-category-id").val() !== "") {
        QuizillaRest.fetchFromUrl("api/categories/" + $("#edit-category-id").val(), function(category) {
            selectedCategory = category;
            fillCategoryEditForm();
        });
    } else {
        fillCategoryEditForm();
    }

    function fillCategoryEditForm() {
        if (selectedCategory) {
            $("#edit-category-code").val(selectedCategory.code);
            $("#edit-category-name").val(selectedCategory.name);
            $("#edit-category-description").val(selectedCategory.description);
        }

        $("#edit-category-code").focus();
    }
};

QuizillaUtil.onLanguageDialogShown = function() {
    var selectedLanguage = null;
    if ($("#edit-language-id").val() !== "") {
        QuizillaRest.fetchFromUrl("api/languages/" + $("#edit-language-id").val(), function(language) {
            selectedLanguage = language;
            fillLanguageEditForm();
        });
    } else {
        fillLanguageEditForm();
    }

    function fillLanguageEditForm() {
        if (selectedLanguage) {
            $("#edit-language-code").val(selectedLanguage.code);
            $("#edit-language-name").val(selectedLanguage.name);
        }

        $("#edit-language-code").focus();
    }
};


// onDialogHidden events
QuizillaUtil.onQuestionDialogHidden = function() {
    $("#edit-question-id").val("");
    $("#edit-question-text").val("");
    $("#edit-question-category").html("");
    $("#edit-question-language").html("");
    $("#question-dialog .possible-answers .answer-input-group").each(function() {
        $(this).removeData("for-answer-id");
        $(this).find(".edit-question-answer-radio").prop("checked", false);
        $(this).find(".edit-question-answer-text").val("");
    });
};

QuizillaUtil.onCategoryDialogHidden = function() {
    $("#edit-category-id").val("");
    $("#edit-category-code").val("");
    $("#edit-category-name").val("");
    $("#edit-category-description").val("");
};

QuizillaUtil.onLanguageDialogHidden = function() {
    $("#edit-language-id").val("");
    $("#edit-language-code").val("");
    $("#edit-language-name").val("");
};

// Save button click events
QuizillaUtil.saveQuestion = function() {
    let question = {};
    question.question = $("#edit-question-text").val();
    question.categoryId = $("#edit-question-category").val();
    question.languageId = $("#edit-question-language").val();

    let checkedRadioButton = $("input[name='edit-question-answer-radio']:checked");
    let answerInputGroup = checkedRadioButton.closest(".answer-input-group");
    question.correctAnswerId = answerInputGroup.data("for-answer-id");

    question.answers = {};
    $("#question-dialog .answer-input-group").each(function() {
        let id = $(this).data("for-answer-id");
        let answer = $(this).find(".edit-question-answer-text").val();
        question.answers[id] = answer;
    });

    let questionId = $("#edit-question-id").val();
    let uri = "api/questions" + (questionId ? "/" + questionId : "");
    let method = questionId ? "PUT" : "POST";
    QuizillaRest.saveEntity(uri, question, method, function(data) {
        $("#question-dialog").modal("hide");
        QuizillaUtil.loadQuestionsTableWithData();
        QuizillaUtil.showSuccessMessage("Question saved successfully!");
    });
};

QuizillaUtil.saveCategory = function() {
    let category = {};
    category.code = $("#edit-category-code").val();
    category.name = $("#edit-category-name").val();
    category.description = $("#edit-category-description").val();

    let categoryId = $("#edit-category-id").val();
    let uri = "api/categories" + (categoryId ? "/" + categoryId : "");
    let method = categoryId ? "PUT" : "POST";
    QuizillaRest.saveEntity(uri, category, method, function(data) {
        $("#category-dialog").modal("hide");
        QuizillaUtil.loadCategoriesTableWithData();
        QuizillaUtil.showSuccessMessage("Category saved successfully!");
    });
};

QuizillaUtil.saveLanguage = function() {
    let language = {};
    language.code = $("#edit-language-code").val();
    language.name = $("#edit-language-name").val();

    let langId = $("#edit-language-id").val();
    let uri = "api/languages" + (langId ? "/" + langId : "");
    let method = langId ? "PUT" : "POST";
    QuizillaRest.saveEntity(uri, language, method, function(data) {
        $("#language-dialog").modal("hide");
        QuizillaUtil.loadLanguagesTableWithData();
        QuizillaUtil.showSuccessMessage("Language saved successfully!");
    });
};

QuizillaUtil.showSuccessMessage = function(message) {
    QuizillaUtil.showMessage(message, "success");
};

QuizillaUtil.showErrorMessage = function(message) {
    QuizillaUtil.showMessage(message, "error");
};

QuizillaUtil.showMessage = function(message, prefix) {
    let alertMessage = $(document.createElement("div")).appendTo($("body"));
    alertMessage.addClass("alert alert-" + prefix + " " + prefix + "-message");
    alertMessage.attr("role", "alert");
    alertMessage.html(message);
    alertMessage.fadeIn("slow");
    setTimeout(function() {
        alertMessage.fadeOut("slow", function() {
            $(this).remove();
        });
    }, 5000);
}