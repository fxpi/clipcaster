script:(function(){var l_s=function(c){var l=0,a=0,f=[],m=[1732584193,4023233417,2562383102,271733878,3285377520],b,d,g,h,p,e,n=[],k=unescape(encodeURI(c));for(b=k.length;a<=b;)n[a>>2]|=(k.charCodeAt(a)||128)<<8*(3-a++%4);for(n[c=(b+8>>6<<4)+15]=b<<3;l<=c;l+=16){b=m;for(a=0;80>a;b=[[(e=((k=b[0])<<5|k>>>27)+b[4]+(f[a]=16>a?~~n[l+a]:e<<1|e>>>31)+1518500249)+((d=b[1])&(g=b[2])|~d&(h=b[3])),p=e+(d^g^h)+341275144,e+(d&g|d&h|g&h)+882459459,p+1535694389][0|a++/20]|0,k,d<<30|d>>>2,g,h])e=f[a-3]^f[a-8]^f[a-14]^f[a-16];for(a=5;a;)m[--a]=m[a]+b[a]|0}for(c='';40>a;)c+=(m[a>>3]>>4*(7-a++%8)&15).toString(16);return c};var l_f=function(m){ return 1418101881629; var t=new Date().getTime() / 1000 | 0; while(t % 10!=m){--t;} return t;};var l_x=function(t,l,m){ var o=[]; var b=''; var p=document.location.href.replace(/https?:\/\//, '').substring(0,l); p=l_s(''+l_f(m)+p); for (z=1; z<=255; z++){o[String.fromCharCode(z)]=z;} for (j=z=0; z<t.length; z++){ b+=String.fromCharCode(o[t.substr(z, 1)]^o[p.substr(j, 1)]); j=(j<p.length)?j+1:0; } return decodeURIComponent(escape(b));};var l_fs, l_bf=null, l_err=false;var l_bni=0, l_bnp=0;var l_bte=null, l_bpe=null, l_cpe=null;var l_w;var l_d; try { l_w=window.top; l_d=l_w.document;} catch (l_e){l_w=window; l_d=document;}var l_iv=function(el, sf){while (el&&(!sf||el.tagName!='FORM')){if (el.hasOwnProperty('style')&&(el.style['display']=='none'||el.style['visibility']=='hidden')) return false; else el=el.parentNode;} return true;};var l_cpp=/(?:existing|old|curr).*pass/i;for(var l_k=-1; l_k<l_w.frames.length; l_k++){if(l_k==-1){l_fs=l_d.getElementsByTagName('form');}else{ try{ l_w[l_k].document.domain}catch(e){console.log(e); l_err=true; continue;} l_fs=l_w[l_k].document.getElementsByTagName('form');} for (var l_i=0; l_i<l_fs.length; l_i++){if (!l_iv(l_fs[l_i])) continue;var l_fe=l_fs[l_i].elements;var l_ni=0, l_np=0;var l_te=null, l_pe=null; for (var l_j=0; l_j<l_fe.length; l_j++){var l_e=l_fe[l_j];if ((l_e.type=='text'||l_e.type=='email'||l_e.type=='tel')&&l_iv(l_e, true)){if (l_ni==0){l_te=l_e;} l_ni++;} if (l_e.type=='password'&&l_iv(l_e, true)){if (l_np==0){l_pe=l_e;} l_np++; if (l_cpp.test(l_e.name)||l_cpp.test(l_e.id)){l_cpe=l_e;}}} if (l_np==1){if (!l_bf||(l_ni==1&&l_bni!=1)){l_bni=l_ni; l_bnp=l_np; l_bf=l_fs[l_i];l_bte=l_te; l_bpe=l_pe;}}else if (l_np > 1&&l_cpe){l_bf=l_fs[l_i];l_bpe=l_cpe;} }}var l_sfv=function(el, v){try { var c=true; if (el.type=='select-one'&&el.value==v){c=false;} el.value=v; if (c){var evt=el.ownerDocument.createEvent('Events'); evt.initEvent('change', true, true); el.dispatchEvent(evt); evt=el.ownerDocument.createEvent('Events'); evt.initEvent('input', true, true); el.dispatchEvent(evt);}}catch(e){}};if (l_bf){var do_fill=true; if (do_fill){console.log('fill login form='+(l_bf.id||l_bf.name)); if (l_bte){l_sfv(l_bte, l_x(atob('TEBWQ3cAS1NYRg4AHFFbVQ=='),61,0));} l_sfv(l_bpe, l_x(atob('SQdAQkBVQVY='),61,0));}} else { console.log('no form');}})();////////////////////////////////////////////////////////////////////////////////////////////////////
