// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// BEGIN EXTRA CODE
// END EXTRA CODE
/**
 * @param {string} className - Enter the class name of the element you want to target.
 * @param {boolean} targetParentElement - If you want to scroll the parent element into view (because of a textfield above the validation feedback) set this to true.
 * @returns {Promise.<boolean>}
 */
async function ScrollToClass(className, targetParentElement) {
    var _a;
    // BEGIN USER CODE
    if (!className.startsWith(".")) {
        className = "." + className;
    }
    const element = document.querySelector(className);
    if (element) {
        if (targetParentElement) {
            (_a = element.parentElement) === null || _a === void 0 ? void 0 : _a.scrollIntoView();
        }
        else {
            element.scrollIntoView();
        }
        return true;
    }
    else {
        return false;
    }
    // END USER CODE
}

export { ScrollToClass };
