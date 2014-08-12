$ ->
 $.get "/quotes", (data) ->
  $.each data, (createNewQuote, quote) ->
   $("#quotes").append $("<li>").text quote.name