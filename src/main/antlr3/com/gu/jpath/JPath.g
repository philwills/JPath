grammar JPath;

@header {
package com.gu.jpath;
}

query returns[List<QueryItem> result]
scope { List identifiers; }
@init {
	$query::identifiers = new ArrayList<QueryItem>();
}
	:(
		queryItem (OPERATOR queryItem)*
		EOF
	 )
	 { $result = $query::identifiers; }
	;

queryItem
	:	(i=identifier ARRAY_ACCESS_START d=digit ARRAY_ACCESS_END)
	{ $query::identifiers.add(new ArrayAccessQueryItem(i, d)); }
	|
	(i=identifier)
	{ $query::identifiers.add(new DirectAccessQueryItem(i)); }
	;

identifier returns [String id]
	:	i=IDENTIFIER { $id = $i.getText(); }
	|
	;

digit returns [Integer digit]
	:	d=DIGIT { $digit = new Integer($d.getText()); }
	|
	;

IDENTIFIER : ('a'..'z'|'A'..'Z'|'-'|'_')+;
OPERATOR: '.';
ARRAY_ACCESS_START: '[';
DIGIT: '0'..'9'+;
ARRAY_ACCESS_END: ']';
EOF: '<EOF>';