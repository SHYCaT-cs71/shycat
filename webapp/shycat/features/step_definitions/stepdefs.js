const { format, parse, parseISO } = require("date-fns");

const assert = require("assert");
const { Given, When, Then } = require("@cucumber/cucumber");
const { formatDate } = require("../../src/data/Utilities");

Given('event start date is {string}', function (startDate) {
    this.startDate = startDate;
});

When('the event is displayed', function () {
    this.actualAnswer = formatDate(this.startDate);
});

Then('it should be displayed as {string}', function (output) {
    assert.strictEqual(this.actualAnswer, output);
});
