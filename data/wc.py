import urllib, bs4, random, subprocess 
import urllib2  

words = [i.split("\n")[0] for i in open('./cracklib-small')]

while True:
        try:
                word = random.choice(words)
                urlname = "https://www.google.com/search?hl=en&q=" + word  + "&biw=1366&bih=655&tbm=isch&source=lnt&tbs=isz:ex,iszw:800,iszh:800&sa=X&ei=739TUtjVE_Sh4AO93YDYBw&ved=0CCoQpwUoBQ"
                hdr = {'User-Agent': 'Mozilla/5.0'}
                req = urllib2.Request(urlname, headers=hdr)
                page = bs4.BeautifulSoup(urllib.urlopen(req))
                pagestr = str(page)
                turl = pagestr.split('imgurl=')[1].split('&amp')[0]
                name = turl.split("/")[-1]
                urllib.urlretrieve(turl,"./images/" + name) 
                break

        except:
                print("some failure")
                continue
        break
