let allHeadCheckboxes = $("table thead .checkbox-col input[type='checkbox']");

$(function() {
    loadQuestions();
    handleTabSwitchingBehavior();
    handleCheckboxesBehavior();
});

function handleTabSwitchingBehavior() {
    $("a[data-toggle='tab']").on("shown.bs.tab", function(e) {
        allHeadCheckboxes.prop("checked", false);

        let tab = $(e.target).data("tab");
        if (tab === "questions") {
            loadQuestions();
        } else if (tab === "categories") {
            loadCategories();
        } else if (tab === "languages") {
            loadLanguages();
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

function loadQuestions() {
    let tableBody = QuizillaUtil.getClearTableBody($(".tab-content #questions #questions-table tbody"));

    fetch("api/questions")
        .then((response) => response.json())
        .then((questions) => {
            questions.forEach((question, i) => {
                let rowElem = $(document.createElement("tr")).appendTo(tableBody);

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

function loadCategories() {
    let tableBody = QuizillaUtil.getClearTableBody($(".tab-content #categories #categories-table tbody"));

    fetch("api/categories")
        .then((response) => response.json())
        .then((categories) => {
            categories.forEach((category, i) => {
                let rowElem = $(document.createElement("tr")).appendTo(tableBody);

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

function loadLanguages() {
    let tableBody = QuizillaUtil.getClearTableBody($(".tab-content #languages #languages-table tbody"));

    fetch("api/languages")
        .then((response) => response.json())
        .then((languages) => {
            languages.forEach((language, i) => {
                let rowElem = $(document.createElement("tr")).appendTo(tableBody);

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