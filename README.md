# Web crawler

**How to build, test and run your solution.**

__mvn clean compile assembly:single__
<br />
__java -jar wipro-buildit-web-crawler-1.0-SNAPSHOT-jar-with-dependencies__
<br />
Result is in results.txt
**Reasoning and describe any trade offs.**
I normalize the URL so I cut out parameters part and take only the core URL. 
Depending on params maybe there would be different content.

**Explanation of what could be done as a future extension.**
Test for DomainNarrowedCrawler -> it should be used Mock Http server and provide html pages within localhost domain. <br />
Also it would be nice to do a white list to not include js or php files into processing.
<br />
And now crawler works in single thread, it could be a multithread application 
<br />
Take into account parameters part of URL