const { format, parse, parseISO } = require("date-fns");

// Move function to separate Utilities file that uses "require"
// so it can also be used by Cucumber CommonJS style with require

const formatDate = (dateString) => {
    try {
        const parsedDate = dateString.includes('T')
            ? parseISO(dateString)
            : parse(dateString, 'yyyy-MM-dd', new Date());
        const formatString = dateString.includes('T') ? 'MMMM do, yyyy h:mm a' : 'MMMM do, yyyy';
        return format(parsedDate, formatString);
    } catch {
        return 'Invalid Date';
    }
};

// Explicitly export as part of module for use by Cucumber
module.exports.formatDate = formatDate;
