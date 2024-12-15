// jest-dom adds custom jest matchers for asserting on DOM nodes.
// allows you to do things like:
// expect(element).toHaveTextContent(/react/i)
// learn more: https://github.com/testing-library/jest-dom
import '@testing-library/jest-dom';

// Adds the 'fetchMock' global variable and rewires 'fetch' global to call 'fetchMock' instead of the real implementation
// See https://github.com/jefflau/jest-fetch-mock
// Note that using this line also required adding the "resetMocks": false entry to the jest config in package.json
// If that's removed, then `beforeEach(() => fetchMock.enableMocks());` will need to be added to each test
// for which fetch should be mocked
require("jest-fetch-mock").enableMocks();
