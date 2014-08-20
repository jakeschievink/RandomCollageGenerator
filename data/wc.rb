require 'open-uri'
require 'rubygems' 
require 'nokogiri'
begin
        url = "https://www.google.com/search?hl=en&q=" + rand(0-999999).to_s + "&ion=1&bav=on.2,or.r_gc.r_pw.r_cp.r_qf.&bvm=bv.42553238,d.dmg&biw=1354&bih=622&um=1&ie=UTF-8&tbm=isch&source=og&sa=N&tab=wi&ei=sNEfUf-fHvLx0wG7uoG4DQ"
        googim = Nokogiri::HTML(open(url))
        googimstr = googim.to_s
        durl = googim.to_s.split('imgurl=')[1].split('&amp')[0]

        name = durl.reverse.split("/")[0].reverse

        #    open("./data/images/#{name}", 'wb') do |file|
        #             file << open(durl).read
        #    end

        open("./images/#{name}", 'wb') do |file|
           file << open(durl).read
        end
rescue => e
          puts "some failure" 
          retry
end
