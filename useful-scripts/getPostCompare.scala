import scala.collection.mutable.Map

val l = scala.io.Source.fromFile(args(0)).getLines.toList.map(_.split('\t')).map(x=>(x.apply(0),x.apply(1)))
//l.distinct.map(x=>(x, l.count(_==x))).sortBy(x=>(-x._2)).foreach(x=>println(x._1+" "+x._2))

def freq(l: List[(String, String)]): Map[String,(Double,Double)] = {
	val ans = scala.collection.mutable.Map.empty[String, (Double,Double)]
	var connect = 0
	for((host, method) <- l){
		val (gCount,pCount) = ans.getOrElse(host,(0.0,0.0))
		if(method=="GET")ans+=(host ->(gCount+1, pCount))
		else if(method=="POST")ans+=(host ->(gCount, pCount+1))
		else if(method=="CONNECT") connect+=1
		else if(method != "-") println(method)
	}
	ans
}
freq(l).toList.filterNot(x=>x._2._2==0.0).sortBy(x=>((x._2._1)/(x._2._2))).foreach(x=>println(x._1+" "+x._2._1+" "+x._2._2+" "+x._2._1/x._2._2))

