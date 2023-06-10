package com.example.btl_mobile.DAO

import com.example.btl_mobile.Model.Match
import com.example.btl_mobile.Model.News
import com.example.btl_mobile.Model.Team

import org.jsoup.Jsoup

object Controller
{
    var listTeam : ArrayList<Team> = ArrayList()
    var listMatch:ArrayList<Match> = ArrayList()
    var listNews:ArrayList<News> = ArrayList()

    fun getData()
    {
        getTeam()
        getPoint()
    }

    fun getListTeamSorted():List<Team> {
        var sortedList = listTeam.sortedBy {it.rank  }
        return sortedList
    }

    fun getSortedMatch():List<Match>
    {
        var sortedList = listMatch.sortedBy { it.time }.sortedBy { it.day }
        return sortedList
    }

    fun getPoint()
    {
        var doc = Jsoup.connect("https://www.premierleague.com/tables").get()
        var teamWrapper = doc.getElementsByClass("tableBodyContainer")[0]
        var listTeamW = teamWrapper.getElementsByTag("tr")
        var x = 0
        for(team in listTeamW)
        {
            x++;
            if(x%2==0) continue
            var name :String = team.attr("data-filtered-table-row-name").toString()
            var point :String = team.getElementsByClass("points")[0].ownText()
            var rank:Int = team.getElementsByClass("pos button-tooltip")[0].getElementsByTag("span")[0].ownText().toString().toInt()
            var won:String = team.getElementsByTag("td")[4].ownText()
            var lost:String= team.getElementsByTag("td")[6].ownText()
            var drawn:String = team.getElementsByTag("td")[5].ownText()

            for(team1 in listTeam)
            {
                if(team1.name==name)
                {
                    team1.point=point
                    team1.rank=rank
                    team1.won = won
                    team1.lost=lost
                    team1.drawn=drawn
                    break
                }
            }

            var nextMatch = team.getElementsByClass("nextMatchCol hideMed")[0].getElementsByTag("a")[0].getElementsByTag("span")[0].getElementsByClass("matchAbridged")[0]
            var nameNextTeam = nextMatch.getElementsByClass("teamName")[1].getElementsByTag("abbr")[0].attr("title")
            if(nameNextTeam!=name)
            {
                var dayNextMatch:String = nextMatch.getElementsByClass("matchInfo")[0].ownText()
                var timeNextMatch:String =  nextMatch.getElementsByTag("time")[0].ownText()
                listMatch.add(Match(
                    listTeam.find { it.name ==name },
                    listTeam.find { it.name ==nameNextTeam },timeNextMatch,dayNextMatch,""))
//                Log.e(name, nameNextTeam)
            }
        }
    }
    fun getTeam()
    {
        var doc = Jsoup.connect("https://www.premierleague.com/clubs").get()

        var teamWrapper = doc.getElementsByClass("block-list-5 block-list-3-m block-list-1-s block-list-1-xs block-list-padding dataContainer")[0]
        var listTeamW = teamWrapper.getElementsByTag("li")

        for(team in listTeamW)
        {
            var infoTeam = team.getElementsByTag("a")[0]
            var name :String = infoTeam.getElementsByClass("indexInfo clubColourBg")[0].getElementsByClass("nameContainer")[0].getElementsByClass("clubName")[0].ownText()
            var flagUrl:String = infoTeam.getElementsByClass("indexBadge")[0].getElementsByTag("span")[0].getElementsByTag("img")[0].absUrl("src")
            listTeam.add(Team(name,flagUrl,"0",0,"","",""))
        }
    }

    fun getNews()
    {
        var doc = Jsoup.connect("https://www.premierleague.com/news").get()

        var newsWrapper = doc.getElementsByClass("newsList contentListContainer")[0]
        var listNewsW = newsWrapper.getElementsByTag("li")

        var x = 0;
        for(news in listNewsW)
        {
//            x++;
//            if(x>3) break;
            var infoNews = news.getElementsByClass("featuredArticle")[0].getElementsByClass("col-9-m")[0].getElementsByTag("a")[0]
            var link:String = infoNews.attr("href")
            var imgUrl:String = infoNews.getElementsByTag("figure")[0].getElementsByClass("image thumbCrop-news-list")[0].getElementsByTag("img")[0].attr("src").toString()
            var content:String = infoNews.getElementsByTag("figcaption")[0].getElementsByClass("title")[0].ownText()
//            Log.e("Key", imgUrl)
            listNews.add(News(imgUrl.trim(),content,link))
        }
    }


}





















