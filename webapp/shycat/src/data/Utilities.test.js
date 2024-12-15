// For some reason just importing from "Utilities" doesn't work, but this does...
import { formatDate } from '../data/Utilities';

test("formatDate includes time in output when present in input", () => {
    const result = formatDate("2024-12-11T09:30:00");
    expect(result).toEqual("December 11th, 2024 9:30 AM");

    const result2 = formatDate("2024-05-01T15:45:36");
    expect(result2).toEqual("May 1st, 2024 3:45 PM");
});

test("formatDate doesn't include time in output when not present in input", () => {
    const result = formatDate("2024-12-02");
    expect(result).toEqual("December 2nd, 2024");
});
