
import assert from "assert";
import { Given, When, Then } from "@cucumber/cucumber";
import { formatDate } from "../../src/data/Utilities.js";

Given('event start date is {string}', function (startDate) {
    this.startDate = startDate;
});

When('the event is displayed', function () {
    this.actualAnswer = formatDate(this.startDate);
});

Then('it should be displayed as {string}', function (output) {
    assert.strictEqual(this.actualAnswer, output);
});
