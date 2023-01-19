package com.example.DataSetter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;

import com.example.Printer.DataPrinter;
import com.example.Printer.FileShifter;
import com.example.ReportsDto.MrrSummaryDto;
import com.example.ReportsDto.RiverRunDto;
import com.example.ReportsDto.SummaryHpDto;
import com.example.ReportsDto.VantageDto;
import com.example.SegregatorDto.Confluence_SegDto;
import com.example.SegregatorDto.Email_SegDto;
import com.example.SegregatorDto.GlobalRelay_SegDto;
import com.example.SegregatorDto.Mrr_SegDto;
import com.example.SegregatorDto.RingCentral_SegDto;
import com.example.SegregatorDto.RiverRun_SegDto;
import com.example.SegregatorDto.Skype_SegDto;
import com.example.SegregatorDto.SocialMedia_SegDto;
import com.example.SegregatorDto.Zoom_SegDto;

public class ReportDataSegregator {

	static List<Email_SegDto> emailList = new CopyOnWriteArrayList();

	static List<Confluence_SegDto> confluenceList = new CopyOnWriteArrayList<>();

	static List<GlobalRelay_SegDto> globalRelayList = new CopyOnWriteArrayList<>();

	static List<Mrr_SegDto> mrrList = new CopyOnWriteArrayList<>();

	static List<RingCentral_SegDto> ringCentralList = new CopyOnWriteArrayList<>();

	static List<RiverRun_SegDto> riverRunList = new CopyOnWriteArrayList<>();

	static List<Skype_SegDto> skypeList = new CopyOnWriteArrayList<>();

	static List<SocialMedia_SegDto> socialMediaList = new CopyOnWriteArrayList<>();

	static List<Zoom_SegDto> zoomList = new CopyOnWriteArrayList<>();

	static Zoom_SegDto zoom_segDto = new Zoom_SegDto();

	static Email_SegDto email_SegDto = new Email_SegDto();

	static Confluence_SegDto confluence_SegDto = new Confluence_SegDto();

	static GlobalRelay_SegDto globalRelay_SegDto = new GlobalRelay_SegDto();

	static Mrr_SegDto mrr_SegDto = new Mrr_SegDto();

	static RingCentral_SegDto ringCentral_SegDto = new RingCentral_SegDto();

	static RiverRun_SegDto riverRun_SegDto = new RiverRun_SegDto();

	static Skype_SegDto skype_SegDto = new Skype_SegDto();

	static SocialMedia_SegDto socialMedia_SegDto = new SocialMedia_SegDto();

	static DataPrinter dataPrinter = new DataPrinter();

	static FileShifter fileShifter = new FileShifter();

	public void segregatingDataInRespectiveFileds(List<SummaryHpDto> reportDtoList, List<VantageDto> vantageDtoList,
			List<MrrSummaryDto> mrrSummaryDtoList, List<RiverRunDto> riverRunDtoList, List<String> dateList)
			throws IOException {

		for (SummaryHpDto summaryHpDto : reportDtoList) {

			email_SegDto = new Email_SegDto();

			ringCentral_SegDto = new RingCentral_SegDto();

			confluence_SegDto = new Confluence_SegDto();

			socialMedia_SegDto = new SocialMedia_SegDto();

			zoom_segDto = new Zoom_SegDto();

			globalRelay_SegDto = new GlobalRelay_SegDto();

			if (StringUtils.containsIgnoreCase(summaryHpDto.getSource(), "NM-Email")) {

				email_SegDto.setDate(summaryHpDto.getDate());
				email_SegDto.setExchangeToDs(summaryHpDto.getNmExchange());
				email_SegDto.setDsFromExchange(summaryHpDto.getHpExchange());
				email_SegDto.setVariance(summaryHpDto.getDifference());

				emailList.add(email_SegDto);

			}

			if (StringUtils.containsIgnoreCase(summaryHpDto.getSource(), "RingCentral")) {

				ringCentral_SegDto.setDate(summaryHpDto.getDate());
				ringCentral_SegDto.setExchangeToDs(summaryHpDto.getNmExchange());
				ringCentral_SegDto.setDsFromExchange(summaryHpDto.getHpExchange());
				ringCentral_SegDto.setVariance(summaryHpDto.getDifference());

				ringCentralList.add(ringCentral_SegDto);

			}

			if (StringUtils.containsIgnoreCase(summaryHpDto.getSource(), "SocialMedia")) {

				socialMedia_SegDto.setDate(summaryHpDto.getDate());
				socialMedia_SegDto.setExchangeToDs(summaryHpDto.getNmExchange());
				socialMedia_SegDto.setDsFromExchange(summaryHpDto.getHpExchange());
				socialMedia_SegDto.setVariance(summaryHpDto.getDifference());

				socialMediaList.add(socialMedia_SegDto);

			}

			if (StringUtils.containsIgnoreCase(summaryHpDto.getSource(), "Confluence")) {

				confluence_SegDto.setDate(summaryHpDto.getDate());
				confluence_SegDto.setExchangeToDs(summaryHpDto.getNmExchange());
				confluence_SegDto.setDsFromExchange(summaryHpDto.getHpExchange());
				confluence_SegDto.setVariance(summaryHpDto.getDifference());

				confluenceList.add(confluence_SegDto);

			}

			if (StringUtils.containsIgnoreCase(summaryHpDto.getSource(), "Zoom")) {

				zoom_segDto.setDate(summaryHpDto.getDate());
				zoom_segDto.setExchangeToDs(summaryHpDto.getNmExchange());
				zoom_segDto.setDsFromExchange(summaryHpDto.getHpExchange());
				zoom_segDto.setVariance(summaryHpDto.getDifference());

				zoomList.add(zoom_segDto);

			}

			if (StringUtils.containsIgnoreCase(summaryHpDto.getSource(), "GlobalRelay-SlackLog")) {

				globalRelay_SegDto.setDate(summaryHpDto.getDate());
				globalRelay_SegDto.setExchangeToDs(summaryHpDto.getNmExchange());
				globalRelay_SegDto.setDsFromExchange(summaryHpDto.getHpExchange());
				globalRelay_SegDto.setVariance(summaryHpDto.getDifference());

				globalRelayList.add(globalRelay_SegDto);

			}

		}
		for (VantageDto vantageDto : vantageDtoList) {

			skype_SegDto = new Skype_SegDto();

			if (StringUtils.containsIgnoreCase(vantageDto.getContentType(), "Skype-HO")) {

				skype_SegDto.setDate(vantageDto.getDate());
				skype_SegDto.setExchangeToDs(vantageDto.getVantage());
				skype_SegDto.setDsFromExchange(vantageDto.getVantageHp());
				skype_SegDto.setVariance(vantageDto.getDifference());

				skypeList.add(skype_SegDto);

			}

		}
		for (MrrSummaryDto mrrSumaryDto : mrrSummaryDtoList) {

			mrr_SegDto = new Mrr_SegDto();

			if (StringUtils.containsIgnoreCase(mrrSumaryDto.getSource(), "NM-Email")) {

				mrr_SegDto.setDate(mrrSumaryDto.getDate());
				mrr_SegDto.setExchangeToDs(mrrSumaryDto.getNmExchange());
				mrr_SegDto.setDsFromExchange(mrrSumaryDto.getMrr());
				mrr_SegDto.setVariance(mrrSumaryDto.getDifference());

				mrrList.add(mrr_SegDto);
			}

		}
		for (RiverRunDto riverRunDto : riverRunDtoList) {

			riverRun_SegDto = new RiverRun_SegDto();

			if (StringUtils.containsIgnoreCase(riverRunDto.getContentType(), "RiverRun")) {

				riverRun_SegDto.setDate(riverRunDto.getDate());
				riverRun_SegDto.setExchangeToDs(riverRunDto.getRiverRunData());
				riverRun_SegDto.setDsFromExchange(riverRunDto.getHp());
				riverRun_SegDto.setVariance(riverRunDto.getDifference());

				riverRunList.add(riverRun_SegDto);

			}

		}

		confluenceList.forEach(n -> System.out.println(n));

		emailList.forEach(n -> System.out.println(n));

		globalRelayList.forEach(n -> System.out.println(n));

		mrrList.forEach(n -> System.out.println(n));

		ringCentralList.forEach(n -> System.out.println(n));

		riverRunList.forEach(n -> System.out.println(n));

		skypeList.forEach(n -> System.out.println(n));

		socialMediaList.forEach(n -> System.out.println(n));

		zoomList.forEach(n -> System.out.println(n));

		String fileLocation = fileShifter.copyOutlineContents();

		dataPrinter.printAllDatainExcel(confluenceList, emailList, globalRelayList, mrrList, ringCentralList,
				riverRunList, skypeList, socialMediaList, zoomList, dateList, fileLocation);

	}

}
