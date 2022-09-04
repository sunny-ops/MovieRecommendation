const how=new XMLHttpRequest()
$('.container').hide()
 $(document).ready(function(){
    let movie=$('#imdb')
    $(document).one('click',"#on",function(ef){
    ef.preventDefault();
        
        how.addEventListener('readystatechange',(e)=>{
            if(e.target.readyState===4){
                const data=JSON.parse(e.target.responseText)
                console.log(data)
                if(data.Response!="False"){
                $( ".container" ).slideDown( "slow", function() {
                $('#img').attr("src",data.Poster)
                $('.tit').append(data.Title)
                $('.ok1').append(data.Ratings[0].Value)
                if(data.Ratings.length>1){  
                $('.ok2').append(data.Ratings[1].Value)
                }
                $('.tit2').append(data.Plot)
                $('.tit3').append(data.Year)
                });
            }else{
                $(".container").slideDown("slow",function(){
                    $('.tit,.tit2,.tit3,.ok1, .ok2').append("Movie Not Found")
                   
                })
            }
                
            }
        })
        $('#rel').click(function(){
            location.reload()
    });
    how.open('GET', `http://www.omdbapi.com/?t=${movie.val()}&apikey=904d5f41`)
    how.send()
    })
 })
