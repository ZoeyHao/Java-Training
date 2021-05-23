package com.persion.javatraining.week3.nio02.gateway.inbound;

import com.persion.javatraining.week3.MyFilter;
import com.persion.javatraining.week3.nio02.gateway.filter.HeaderHttpRequestFilter;
import com.persion.javatraining.week3.nio02.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {
	
	private List<String> proxyServer;
	private MyFilter filter;
	
	public HttpInboundInitializer(List<String> proxyServer) {
		this.proxyServer = proxyServer;
		this.filter= new MyFilter();
	}
	
	@Override
	public void initChannel(SocketChannel ch) {
		ChannelPipeline p = ch.pipeline();
//		if (sslCtx != null) {
//			p.addLast(sslCtx.newHandler(ch.alloc()));
//		}
		p.addLast(new HttpServerCodec());
		//p.addLast(new HttpServerExpectContinueHandler());
		p.addLast(new HttpObjectAggregator(1024 * 1024));
		p.addLast(new HttpInboundHandler(this.proxyServer,this.filter));
	}
}
