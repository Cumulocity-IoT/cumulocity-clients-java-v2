// Copyright (c) 2014-2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Device capability to either display or display and manage the WAN, LAN, and DHCP settings.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yNetwork {

	/**
	 * <p>Local network information.</p>
	 */
	@JsonProperty(value = "c8y_LAN")
	private C8yLAN c8yLAN;

	/**
	 * <p>Mobile internet connectivity interface status.</p>
	 */
	@JsonProperty(value = "c8y_WAN")
	private C8yWAN c8yWAN;

	/**
	 * <p>Information for DHCP server status.</p>
	 */
	@JsonProperty(value = "c8y_DHCP")
	private C8yDHCP c8yDHCP;

	public C8yLAN getC8yLAN() {
		return c8yLAN;
	}
	
	public void setC8yLAN(final C8yLAN c8yLAN) {
		this.c8yLAN = c8yLAN;
	}

	public C8yWAN getC8yWAN() {
		return c8yWAN;
	}
	
	public void setC8yWAN(final C8yWAN c8yWAN) {
		this.c8yWAN = c8yWAN;
	}

	public C8yDHCP getC8yDHCP() {
		return c8yDHCP;
	}
	
	public void setC8yDHCP(final C8yDHCP c8yDHCP) {
		this.c8yDHCP = c8yDHCP;
	}

	/**
	 * <p>Local network information.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class C8yLAN {
	
		/**
		 * <p>Subnet mask configured for the network interface.</p>
		 */
		private String netmask;
	
		/**
		 * <p>IP address configured for the network interface.</p>
		 */
		private String ip;
	
		/**
		 * <p>Identifier for the network interface.</p>
		 */
		private String name;
	
		/**
		 * <p>Indicator showing if the interface is enabled.</p>
		 */
		private int enabled;
	
		/**
		 * <p>MAC address of the network interface.</p>
		 */
		private String mac;
	
		public String getNetmask() {
			return netmask;
		}
		
		public void setNetmask(final String netmask) {
			this.netmask = netmask;
		}
	
		public String getIp() {
			return ip;
		}
		
		public void setIp(final String ip) {
			this.ip = ip;
		}
	
		public String getName() {
			return name;
		}
		
		public void setName(final String name) {
			this.name = name;
		}
	
		public int getEnabled() {
			return enabled;
		}
		
		public void setEnabled(final int enabled) {
			this.enabled = enabled;
		}
	
		public String getMac() {
			return mac;
		}
		
		public void setMac(final String mac) {
			this.mac = mac;
		}
	
		@Override
		public String toString() {
			try {
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
		@Override
		public boolean equals(final Object r) {
			if (r != null && r instanceof C8yLAN) {
				C8yLAN comparer = (C8yLAN) r;
				if (String.valueOf(comparer.getNetmask()).equals(String.valueOf(this.getNetmask())) && String.valueOf(comparer.getIp()).equals(String.valueOf(this.getIp())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && Integer.valueOf(comparer.getEnabled()).equals(Integer.valueOf(this.getEnabled())) && String.valueOf(comparer.getMac()).equals(String.valueOf(this.getMac()))) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * <p>Mobile internet connectivity interface status.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class C8yWAN {
	
		/**
		 * <p>SIM connectivity password.</p>
		 */
		private String password;
	
		/**
		 * <p>SIM connection status.</p>
		 */
		private String simStatus;
	
		/**
		 * <p>Authentication type used by the SIM connectivity.</p>
		 */
		private String authType;
	
		/**
		 * <p>APN used for internet access.</p>
		 */
		private String apn;
	
		/**
		 * <p>SIM connectivity username.</p>
		 */
		private String username;
	
		public String getPassword() {
			return password;
		}
		
		public void setPassword(final String password) {
			this.password = password;
		}
	
		public String getSimStatus() {
			return simStatus;
		}
		
		public void setSimStatus(final String simStatus) {
			this.simStatus = simStatus;
		}
	
		public String getAuthType() {
			return authType;
		}
		
		public void setAuthType(final String authType) {
			this.authType = authType;
		}
	
		public String getApn() {
			return apn;
		}
		
		public void setApn(final String apn) {
			this.apn = apn;
		}
	
		public String getUsername() {
			return username;
		}
		
		public void setUsername(final String username) {
			this.username = username;
		}
	
		@Override
		public String toString() {
			try {
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
		@Override
		public boolean equals(final Object r) {
			if (r != null && r instanceof C8yWAN) {
				C8yWAN comparer = (C8yWAN) r;
				if (String.valueOf(comparer.getPassword()).equals(String.valueOf(this.getPassword())) && String.valueOf(comparer.getSimStatus()).equals(String.valueOf(this.getSimStatus())) && String.valueOf(comparer.getAuthType()).equals(String.valueOf(this.getAuthType())) && String.valueOf(comparer.getApn()).equals(String.valueOf(this.getApn())) && String.valueOf(comparer.getUsername()).equals(String.valueOf(this.getUsername()))) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * <p>Information for DHCP server status.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class C8yDHCP {
	
		/**
		 * <p>First configured DNS server.</p>
		 */
		private String dns1;
	
		/**
		 * <p>Second configured DNS server.</p>
		 */
		private String dns2;
	
		/**
		 * <p>Domain name configured for the device.</p>
		 */
		private String domainName;
	
		/**
		 * <p>IP address range.</p>
		 */
		private AddressRange addressRange;
	
		/**
		 * <p>Indicator showing if the DHCP server is enabled.</p>
		 */
		private int enabled;
	
		public String getDns1() {
			return dns1;
		}
		
		public void setDns1(final String dns1) {
			this.dns1 = dns1;
		}
	
		public String getDns2() {
			return dns2;
		}
		
		public void setDns2(final String dns2) {
			this.dns2 = dns2;
		}
	
		public String getDomainName() {
			return domainName;
		}
		
		public void setDomainName(final String domainName) {
			this.domainName = domainName;
		}
	
		public AddressRange getAddressRange() {
			return addressRange;
		}
		
		public void setAddressRange(final AddressRange addressRange) {
			this.addressRange = addressRange;
		}
	
		public int getEnabled() {
			return enabled;
		}
		
		public void setEnabled(final int enabled) {
			this.enabled = enabled;
		}
	
		/**
		 * <p>IP address range.</p>
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class AddressRange {
		
			/**
			 * <p>Start of address range assigned to DHCP clients.</p>
			 */
			private String start;
		
			/**
			 * <p>End of address range assigned to DHCP clients.</p>
			 */
			private String end;
		
			public String getStart() {
				return start;
			}
			
			public void setStart(final String start) {
				this.start = start;
			}
		
			public String getEnd() {
				return end;
			}
			
			public void setEnd(final String end) {
				this.end = end;
			}
		
			@Override
			public String toString() {
				try {
					return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
				} catch (final JsonProcessingException e) {
				}
				return super.toString();
			}
		
			@Override
			public boolean equals(final Object r) {
				if (r != null && r instanceof AddressRange) {
					AddressRange comparer = (AddressRange) r;
					if (String.valueOf(comparer.getStart()).equals(String.valueOf(this.getStart())) && String.valueOf(comparer.getEnd()).equals(String.valueOf(this.getEnd()))) {
						return true;
					}
				}
				return false;
			}
		}
	
		@Override
		public String toString() {
			try {
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
		@Override
		public boolean equals(final Object r) {
			if (r != null && r instanceof C8yDHCP) {
				C8yDHCP comparer = (C8yDHCP) r;
				if (String.valueOf(comparer.getDns1()).equals(String.valueOf(this.getDns1())) && String.valueOf(comparer.getDns2()).equals(String.valueOf(this.getDns2())) && String.valueOf(comparer.getDomainName()).equals(String.valueOf(this.getDomainName())) && comparer.getAddressRange().equals(this.getAddressRange()) && Integer.valueOf(comparer.getEnabled()).equals(Integer.valueOf(this.getEnabled()))) {
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
		}
		return super.toString();
	}

	@Override
	public boolean equals(final Object r) {
		if (r != null && r instanceof C8yNetwork) {
			C8yNetwork comparer = (C8yNetwork) r;
			if (comparer.getC8yLAN().equals(this.getC8yLAN()) && comparer.getC8yWAN().equals(this.getC8yWAN()) && comparer.getC8yDHCP().equals(this.getC8yDHCP())) {
				return true;
			}
		}
		return false;
	}
}
