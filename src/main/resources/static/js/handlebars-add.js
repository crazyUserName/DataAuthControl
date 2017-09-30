 Handlebars.registerHelper("compareAny",function(){
	  var len = arguments.length-1;
	  for (var i = 1; i < len; i++) {
            if(arguments[0] == arguments[i]){
            	 return arguments[len].fn(this);
            }
        }
	  return arguments[len].inverse(this);
});
  
  Handlebars.registerHelper("compareAnd",function(){
	  var len = arguments.length-1;
	  for (var i = 1; i < len; i++) {
            if(arguments[0] != arguments[i]){
            	 return arguments[len].inverse(this);
            }
        }
	  return arguments[len].fn(this);
});