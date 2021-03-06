grammar Numbers;

// entry point of this parser: it parses an input string consisting of at least 
// one number, optionally followed by zero or more comma's and numbers
parse
  :  number (',' number)* EOF
  ;

// matches a number that is between 1 and 3 digits long
number
  :  Digit Digit Digit
  |  Digit Digit
  |  Digit
  ;

// matches a single digit
Digit
  :  '0'..'9'
  ;

// ignore spaces
WhiteSpace
  :  (' ' | '\t' | '\r' | '\n') {skip();}
  ;

