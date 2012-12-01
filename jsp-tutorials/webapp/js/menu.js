/**
 * @author Min Liwn
 */
$(document).ready( function () {

        $('#nav li').hover( function () {
                //show its submenu
                $('ul', this).slideDown(100);

        }, function () {
                //hide its submenu
                $('ul', this).slideUp(100);
        }
        );
        
        $('a').click( function () {
                var page = $(this).text();
                page = page.replace(" ","").toLowerCase();
                if( page != 'sources' && page != 'documents' && page != 'tutorials') {
                        page = "page/" + page + ".html";
                        $('#content').slideUp(1000);
                        document.getElementById('content-frame').src = page;
                        $('#content').slideDown(1000);
                }
        });

});
