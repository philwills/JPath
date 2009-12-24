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
	:	(i=IDENTIFIER
	|
	)
	{ $query::identifiers.add(new DirectAccessQueryItem($i.getText())); }
	;

IDENTIFIER : ('a'..'z'|'A'..'Z'|'-'|'_')+;
OPERATOR: '.';
ARRAY_ACCESS_START: '[';
DIGIT: '0'..'9'+;
ARRAY_ACCESS_END: ']';
EOF: '<EOF>';