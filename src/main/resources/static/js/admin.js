let allHeadCheckboxes = $("table thead .checkbox-col input[type='checkbox']");

$(function() {
    loadQuestionsTableWithData();
    handleTabSwitchingBehavior();
    handleCheckboxesBehavior();
    handleNewEntryButtonClicks();
    handleEditEntryActionIconClicks();
    handleDialogShownAndHiddenEvents();
    handleDialogSaveEvents();
});

function handleDialogSaveEvents() {
    $("#question-dialog #btn-save-question").click(QuizillaUtil.onQuestionSaveButtonClicked);
    $("#category-dialog #btn-save-category").click(QuizillaUtil.onCategorySaveButtonClicked);
    $("#language-dialog #btn-save-language").click(QuizillaUtil.onLanguageSaveButtonClicked);
};

function handleDialogShownAndHiddenEvents() {
    $("#question-dialog").on("shown.bs.modal", QuizillaUtil.onQuestionDialogShown);
    $("#category-dialog").on("shown.bs.modal", QuizillaUtil.onCategoryDialogShown);
    $("#language-dialog").on("shown.bs.modal", QuizillaUtil.onLanguageDialogShown);

    $("#question-dialog").on("hidden.bs.modal", QuizillaUtil.onQuestionDialogHidden);
    $("#category-dialog").on("hidden.bs.modal", QuizillaUtil.onCategoryDialogHidden);
    $("#language-dialog").on("hidden.bs.modal", QuizillaUtil.onLanguageDialogHidden);
};

function handleNewEntryButtonClicks() {
    $(".btn-add-new-entry").click(function() {
        if ($(this).hasClass("btn-add-new-question")) {
            QuizillaUtil.showModalDialog("question-dialog");
        } else if ($(this).hasClass("btn-add-new-category")) {
            QuizillaUtil.showModalDialog("category-dialog");
        } else if ($(this).hasClass("btn-add-new-language")) {
            QuizillaUtil.showModalDialog("language-dialog");
        }
    });
};

function handleEditEntryActionIconClicks() {
    $("#questions-table").on("click", ".edit-action", function() {
        QuizillaUtil.showModalDialog("question-dialog", $(this).data("id-to-edit"), "edit-question-id");
    });

    $("#categories-table").on("click", ".edit-action", function() {
        QuizillaUtil.showModalDialog("category-dialog", $(this).data("id-to-edit"), "edit-category-id");
    });

    $("#languages-table").on("click", ".edit-action", function() {
        QuizillaUtil.showModalDialog("language-dialog", $(this).data("id-to-edit"), "edit-language-id");
    });
};

function handleTabSwitchingBehavior() {
    $("a[data-toggle='tab']").on("shown.bs.tab", function(e) {
        allHeadCheckboxes.prop("checked", false);

        let tab = $(e.target).data("tab");
        if (tab === "questions") {
            loadQuestionsTableWithData();
        } else if (tab === "categories") {
            loadCategoriesTableWithData();
        } else if (tab === "languages") {
            loadLanguagesTableWithData();
        }
    })
};

function handleCheckboxesBehavior() {
    allHeadCheckboxes.click(function() {
        let parentTable = $(this).closest("table");
        let allBodyCheckboxes = parentTable.find("tbody .checkbox-col input[type='checkbox']");
        allBodyCheckboxes.prop("checked", $(this).is(":checked"));
    });
};

function loadQuestionsTableWithData() {
    let tableBody = QuizillaUtil.clearAndGetTableBody("questions-table");

    QuizillaUtil.fetchFromUrl("api/questions", function(questions) {
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

function loadCategoriesTableWithData() {
    let tableBody = QuizillaUtil.clearAndGetTableBody("categories-table");

    QuizillaUtil.fetchFromUrl("api/categories", function(categories) {
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

function loadLanguagesTableWithData() {
    let tableBody = QuizillaUtil.clearAndGetTableBody("languages-table");

    QuizillaUtil.fetchFromUrl("api/languages", function(languages) {
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