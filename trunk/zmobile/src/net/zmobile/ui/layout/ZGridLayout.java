package net.zmobile.ui.layout;

import java.util.Vector;

import net.zmobile.ge.ZPoint;
import net.zmobile.ge.ZRect;
import net.zmobile.ui.ZComponent;
import net.zmobile.ui.ZContainer;

public final class ZGridLayout extends ZLayout {

	public ZGridLayout() {
		numColumns = 1;
		makeColumnsEqualWidth = false;

		horizontalSpacing = 5;
		verticalSpacing = 5;
	}

	public ZGridLayout(int i, boolean flag) {
		numColumns = 1;
		makeColumnsEqualWidth = false;

		horizontalSpacing = 5;
		verticalSpacing = 5;
		numColumns = i;
		makeColumnsEqualWidth = flag;
	}

	ZGridData getData(ZComponent acontrol[][], int i, int j, int k, int l,
			boolean flag) {
		ZComponent control = acontrol[i][j];
		if (control != null) {
			ZGridData griddata = (ZGridData) control.getLayoutData();
			int i1 = Math.max(1, Math.min(griddata.horizontalSpan, l));
			int j1 = Math.max(1, griddata.verticalSpan);
			int k1 = flag ? (i + j1) - 1 : (i - j1) + 1;
			int l1 = flag ? (j + i1) - 1 : (j - i1) + 1;
			if (k1 >= 0 && k1 < k && l1 >= 0 && l1 < l
					&& control == acontrol[k1][l1])
				return griddata;
		}
		return null;
	}

	public ZPoint layout(ZContainer composite) {
		ZRect rectangle = composite.getClientArea();
		return layout(composite, rectangle.x, rectangle.y, rectangle.width,
				rectangle.height);
	}

	ZPoint layout(ZContainer composite, int i, int j, int k, int l) {
		if (numColumns < 1)
			return new ZPoint(0,0);
		Vector acontrol = composite.getComponents();
		int i1 = 0;
		for (int j1 = 0; j1 < acontrol.size(); j1++) {
			ZComponent control = (ZComponent) acontrol.elementAt(j1);
			ZGridData griddata = (ZGridData) control.getLayoutData();
			if (griddata == null || !griddata.exclude)
				acontrol.setElementAt(acontrol.elementAt(j1), i1++);
		}

		if (i1 == 0)
			return new ZPoint(0,0);
		for (int k1 = 0; k1 < i1; k1++) {
			ZComponent control1 = (ZComponent) acontrol.elementAt(k1);
			ZGridData griddata1 = (ZGridData) control1.getLayoutData();
			if (griddata1 == null)
				control1.setLayoutData(griddata1 = new ZGridData());
			griddata1.computeSize(control1, griddata1.widthHint,
					griddata1.heightHint);
			if (griddata1.grabExcessHorizontalSpace
					&& griddata1.minimumWidth > 0
					&& griddata1.cacheWidth < griddata1.minimumWidth) {
				int k2 = 2;
				griddata1.cacheWidth = griddata1.cacheHeight = -1;
				griddata1.computeSize(control1, Math.max(0,
						griddata1.minimumWidth - k2), griddata1.heightHint);
			}
			if (griddata1.grabExcessVerticalSpace
					&& griddata1.minimumHeight > 0)
				griddata1.cacheHeight = Math.max(griddata1.cacheHeight,
						griddata1.minimumHeight);
		}

		int l1 = 0;
		int i2 = 0;
		int j2 = 0;
		int l2 = numColumns;
		ZComponent acontrol1[][] = new ZComponent[4][l2];
		for (int i3 = 0; i3 < i1; i3++) {
			ZComponent control2 = (ZComponent) acontrol.elementAt(i3);
			ZGridData griddata2 = (ZGridData) control2.getLayoutData();
			int l3 = Math.max(1, Math.min(griddata2.horizontalSpan, l2));
			int i4 = Math.max(1, griddata2.verticalSpan);
			do {
				int j4 = l1 + i4;
				if (j4 >= acontrol1.length) {
					ZComponent acontrol2[][] = new ZComponent[j4 + 4][l2];
					System.arraycopy(acontrol1, 0, acontrol2, 0,
							acontrol1.length);
					acontrol1 = acontrol2;
				}
				if (acontrol1[l1] == null)
					acontrol1[l1] = new ZComponent[l2];
				for (; i2 < l2 && acontrol1[l1][i2] != null; i2++)
					;
				int k5 = i2 + l3;
				if (k5 <= l2) {
					int k7;
					for (k7 = i2; k7 < k5 && acontrol1[l1][k7] == null; k7++)
						;
					if (k7 == k5)
						break;
					i2 = k7;
				}
				if (i2 + l3 >= l2) {
					i2 = 0;
					l1++;
				}
			} while (true);
			for (int k4 = 0; k4 < i4; k4++) {
				if (acontrol1[l1 + k4] == null)
					acontrol1[l1 + k4] = new ZComponent[l2];
				for (int l5 = 0; l5 < l3; l5++)
					acontrol1[l1 + k4][i2 + l5] = control2;

			}

			j2 = Math.max(j2, l1 + i4);
			i2 += l3;
		}

		int j3 = k - horizontalSpacing * (l2 - 1);
		int k3 = 0;
		int ai[] = new int[l2];
		int ai1[] = new int[l2];
		boolean aflag[] = new boolean[l2];
		for (int l4 = 0; l4 < l2; l4++) {
			for (int i6 = 0; i6 < j2; i6++) {
				ZGridData griddata3 = getData(acontrol1, i6, l4, j2, l2, true);
				if (griddata3 != null) {
					int i9 = Math
							.max(1, Math.min(griddata3.horizontalSpan, l2));
					if (i9 == 1) {
						int i10 = griddata3.cacheWidth
								+ griddata3.horizontalIndent;
						ai[l4] = Math.max(ai[l4], i10);
						if (griddata3.grabExcessHorizontalSpace) {
							if (!aflag[l4])
								k3++;
							aflag[l4] = true;
						}
						if (!griddata3.grabExcessHorizontalSpace
								|| griddata3.minimumWidth != 0) {
							int j10 = griddata3.grabExcessHorizontalSpace
									&& griddata3.minimumWidth != -1 ? griddata3.minimumWidth
									: griddata3.cacheWidth;
							j10 += griddata3.horizontalIndent;
							ai1[l4] = Math.max(ai1[l4], j10);
						}
					}
				}
			}

			for (int j6 = 0; j6 < j2; j6++) {
				ZGridData griddata4 = getData(acontrol1, j6, l4, j2, l2, false);
				if (griddata4 != null) {
					int j9 = Math
							.max(1, Math.min(griddata4.horizontalSpan, l2));
					if (j9 > 1) {
						int k10 = 0;
						int i11 = 0;
						int i12 = 0;
						for (int i14 = 0; i14 < j9; i14++) {
							k10 += ai[l4 - i14];
							i11 += ai1[l4 - i14];
							if (aflag[l4 - i14])
								i12++;
						}

						if (griddata4.grabExcessHorizontalSpace && i12 == 0) {
							k3++;
							aflag[l4] = true;
						}
						int j14 = (griddata4.cacheWidth + griddata4.horizontalIndent)
								- k10 - (j9 - 1) * horizontalSpacing;
						if (j14 > 0)
							if (makeColumnsEqualWidth) {
								int k16 = (j14 + k10) / j9;
								int i19 = (j14 + k10) % j9;
								int i21 = -1;
								for (int i23 = 0; i23 < j9; i23++)
									ai[i21 = l4 - i23] = Math.max(k16, ai[l4
											- i23]);

								if (i21 > -1)
									ai[i21] += i19;
							} else if (i12 == 0) {
								ai[l4] += j14;
							} else {
								int l16 = j14 / i12;
								int j19 = j14 % i12;
								int j21 = -1;
								for (int j23 = 0; j23 < j9; j23++)
									if (aflag[l4 - j23])
										ai[j21 = l4 - j23] += l16;

								if (j21 > -1)
									ai[j21] += j19;
							}
						if (!griddata4.grabExcessHorizontalSpace
								|| griddata4.minimumWidth != 0) {
							int k14 = griddata4.grabExcessHorizontalSpace
									&& griddata4.minimumWidth != -1 ? griddata4.minimumWidth
									: griddata4.cacheWidth;
							k14 += griddata4.horizontalIndent - i11 - (j9 - 1)
									* horizontalSpacing;
							if (k14 > 0)
								if (i12 == 0) {
									ai1[l4] += k14;
								} else {
									int i17 = k14 / i12;
									int k19 = k14 % i12;
									int k21 = -1;
									for (int k23 = 0; k23 < j9; k23++)
										if (aflag[l4 - k23])
											ai1[k21 = l4 - k23] += i17;

									if (k21 > -1)
										ai1[k21] += k19;
								}
						}
					}
				}
			}

		}

		if (makeColumnsEqualWidth) {
			int i5 = 0;
			int k6 = 0;
			for (int l7 = 0; l7 < l2; l7++) {
				i5 = Math.max(i5, ai1[l7]);
				k6 = Math.max(k6, ai[l7]);
			}

			k6 = k != -1 && k3 != 0 ? Math.max(i5, j3 / l2) : k6;
			for (int i8 = 0; i8 < l2; i8++) {
				aflag[i8] = k3 > 0;
				ai[i8] = k6;
			}

		} else if (k != -1 && k3 > 0) {
			int j5 = 0;
			for (int l6 = 0; l6 < l2; l6++)
				j5 += ai[l6];

			int i7 = k3;
			int j8 = (j3 - j5) / i7;
			int k9 = (j3 - j5) % i7;
			for (int l10 = -1; j5 != j3; l10 = -1) {
				for (int j11 = 0; j11 < l2; j11++)
					if (aflag[j11])
						if (ai[j11] + j8 > ai1[j11]) {
							ai[l10 = j11] = ai[j11] + j8;
						} else {
							ai[j11] = ai1[j11];
							aflag[j11] = false;
							i7--;
						}

				if (l10 > -1)
					ai[l10] += k9;
				for (int k11 = 0; k11 < l2; k11++) {
					for (int j12 = 0; j12 < j2; j12++) {
						ZGridData griddata6 = getData(acontrol1, j12, k11, j2,
								l2, false);
						if (griddata6 != null) {
							int j17 = Math.max(1, Math.min(
									griddata6.horizontalSpan, l2));
							if (j17 > 1
									&& (!griddata6.grabExcessHorizontalSpace || griddata6.minimumWidth != 0)) {
								int l19 = 0;
								int l21 = 0;
								for (int l23 = 0; l23 < j17; l23++) {
									l19 += ai[k11 - l23];
									if (aflag[k11 - l23])
										l21++;
								}

								int i24 = griddata6.grabExcessHorizontalSpace
										&& griddata6.minimumWidth != -1 ? griddata6.minimumWidth
										: griddata6.cacheWidth;
								i24 += griddata6.horizontalIndent - l19
										- (j17 - 1) * horizontalSpacing;
								if (i24 > 0)
									if (l21 == 0) {
										ai[k11] += i24;
									} else {
										int k25 = i24 / l21;
										int k26 = i24 % l21;
										int l27 = -1;
										for (int i29 = 0; i29 < j17; i29++)
											if (aflag[k11 - i29])
												ai[l27 = k11 - i29] += k25;

										if (l27 > -1)
											ai[l27] += k26;
									}
							}
						}
					}

				}

				if (i7 == 0)
					break;
				j5 = 0;
				for (int l11 = 0; l11 < l2; l11++)
					j5 += ai[l11];

				j8 = (j3 - j5) / i7;
				k9 = (j3 - j5) % i7;
			}

		}
		ZGridData agriddata[] = (ZGridData[]) null;
		int j7 = 0;
		if (k != -1) {
			for (int k8 = 0; k8 < l2; k8++) {
				for (int l9 = 0; l9 < j2; l9++) {
					ZGridData griddata5 = getData(acontrol1, l9, k8, j2, l2,
							false);
					if (griddata5 != null && griddata5.heightHint == -1) {
						ZComponent control3 = acontrol1[l9][k8];
						int k12 = Math.max(1, Math.min(
								griddata5.horizontalSpan, l2));
						int l14 = 0;
						for (int k17 = 0; k17 < k12; k17++)
							l14 += ai[k8 - k17];

						l14 += (k12 - 1) * horizontalSpacing
								- griddata5.horizontalIndent;
						if (l14 != griddata5.cacheWidth
								&& griddata5.horizontalAlignment == 4
								|| griddata5.cacheWidth > l14) {
							int l17 = 0;
							l17 = 2;
							griddata5.cacheWidth = griddata5.cacheHeight = -1;
							griddata5.computeSize(control3, Math.max(0, l14
									- l17), griddata5.heightHint);
							if (griddata5.grabExcessVerticalSpace
									&& griddata5.minimumHeight > 0)
								griddata5.cacheHeight = Math.max(
										griddata5.cacheHeight,
										griddata5.minimumHeight);
							if (agriddata == null)
								agriddata = new ZGridData[i1];
							agriddata[j7++] = griddata5;
						}
					}
				}

			}

		}
		int l8 = l - verticalSpacing * (j2 - 1);
		k3 = 0;
		int ai2[] = new int[j2];
		int ai3[] = new int[j2];
		boolean aflag1[] = new boolean[j2];
		for (int l12 = 0; l12 < j2; l12++) {
			for (int i15 = 0; i15 < l2; i15++) {
				ZGridData griddata7 = getData(acontrol1, l12, i15, j2, l2, true);
				if (griddata7 != null) {
					int i20 = Math.max(1, Math.min(griddata7.verticalSpan, j2));
					if (i20 == 1) {
						int i22 = griddata7.cacheHeight
								+ griddata7.verticalIndent;
						ai2[l12] = Math.max(ai2[l12], i22);
						if (griddata7.grabExcessVerticalSpace) {
							if (!aflag1[l12])
								k3++;
							aflag1[l12] = true;
						}
						if (!griddata7.grabExcessVerticalSpace
								|| griddata7.minimumHeight != 0) {
							int j22 = griddata7.grabExcessVerticalSpace
									&& griddata7.minimumHeight != -1 ? griddata7.minimumHeight
									: griddata7.cacheHeight;
							j22 += griddata7.verticalIndent;
							ai3[l12] = Math.max(ai3[l12], j22);
						}
					}
				}
			}

			for (int j15 = 0; j15 < l2; j15++) {
				ZGridData griddata8 = getData(acontrol1, l12, j15, j2, l2,
						false);
				if (griddata8 != null) {
					int j20 = Math.max(1, Math.min(griddata8.verticalSpan, j2));
					if (j20 > 1) {
						int k22 = 0;
						int j24 = 0;
						int l25 = 0;
						for (int l26 = 0; l26 < j20; l26++) {
							k22 += ai2[l12 - l26];
							j24 += ai3[l12 - l26];
							if (aflag1[l12 - l26])
								l25++;
						}

						if (griddata8.grabExcessVerticalSpace && l25 == 0) {
							k3++;
							aflag1[l12] = true;
						}
						int i27 = (griddata8.cacheHeight + griddata8.verticalIndent)
								- k22 - (j20 - 1) * verticalSpacing;
						if (i27 > 0)
							if (l25 == 0) {
								ai2[l12] += i27;
							} else {
								int i28 = i27 / l25;
								int j29 = i27 % l25;
								int l30 = -1;
								for (int l31 = 0; l31 < j20; l31++)
									if (aflag1[l12 - l31])
										ai2[l30 = l12 - l31] += i28;

								if (l30 > -1)
									ai2[l30] += j29;
							}
						if (!griddata8.grabExcessVerticalSpace
								|| griddata8.minimumHeight != 0) {
							int j27 = griddata8.grabExcessVerticalSpace
									&& griddata8.minimumHeight != -1 ? griddata8.minimumHeight
									: griddata8.cacheHeight;
							j27 += griddata8.verticalIndent - j24 - (j20 - 1)
									* verticalSpacing;
							if (j27 > 0)
								if (l25 == 0) {
									ai3[l12] += j27;
								} else {
									int j28 = j27 / l25;
									int k29 = j27 % l25;
									int i31 = -1;
									for (int i32 = 0; i32 < j20; i32++)
										if (aflag1[l12 - i32])
											ai3[i31 = l12 - i32] += j28;

									if (i31 > -1)
										ai3[i31] += k29;
								}
						}
					}
				}
			}

		}

		if (l != -1 && k3 > 0) {
			int i13 = 0;
			for (int k15 = 0; k15 < j2; k15++)
				i13 += ai2[k15];

			int l15 = k3;
			int i18 = (l8 - i13) / l15;
			int k20 = (l8 - i13) % l15;
			for (int l22 = -1; i13 != l8; l22 = -1) {
				for (int k24 = 0; k24 < j2; k24++)
					if (aflag1[k24])
						if (ai2[k24] + i18 > ai3[k24]) {
							ai2[l22 = k24] = ai2[k24] + i18;
						} else {
							ai2[k24] = ai3[k24];
							aflag1[k24] = false;
							l15--;
						}

				if (l22 > -1)
					ai2[l22] += k20;
				for (int l24 = 0; l24 < j2; l24++) {
					for (int i26 = 0; i26 < l2; i26++) {
						ZGridData griddata10 = getData(acontrol1, l24, i26, j2,
								l2, false);
						if (griddata10 != null) {
							int k28 = Math.max(1, Math.min(
									griddata10.verticalSpan, j2));
							if (k28 > 1
									&& (!griddata10.grabExcessVerticalSpace || griddata10.minimumHeight != 0)) {
								int l29 = 0;
								int j31 = 0;
								for (int j32 = 0; j32 < k28; j32++) {
									l29 += ai2[l24 - j32];
									if (aflag1[l24 - j32])
										j31++;
								}

								int k32 = griddata10.grabExcessVerticalSpace
										&& griddata10.minimumHeight != -1 ? griddata10.minimumHeight
										: griddata10.cacheHeight;
								k32 += griddata10.verticalIndent - l29
										- (k28 - 1) * verticalSpacing;
								if (k32 > 0)
									if (j31 == 0) {
										ai2[l24] += k32;
									} else {
										int i33 = k32 / j31;
										int k33 = k32 % j31;
										int l33 = -1;
										for (int i34 = 0; i34 < k28; i34++)
											if (aflag1[l24 - i34])
												ai2[l33 = l24 - i34] += i33;

										if (l33 > -1)
											ai2[l33] += k33;
									}
							}
						}
					}

				}

				if (l15 == 0)
					break;
				i13 = 0;
				for (int i25 = 0; i25 < j2; i25++)
					i13 += ai2[i25];

				i18 = (l8 - i13) / l15;
				k20 = (l8 - i13) % l15;
			}

		}
		int j13 = j;
		for (int i16 = 0; i16 < j2; i16++) {
			int j18 = i;
			for (int l20 = 0; l20 < l2; l20++) {
				ZGridData griddata9 = getData(acontrol1, i16, l20, j2, l2, true);
				if (griddata9 != null) {
					int j25 = Math.max(1, Math
							.min(griddata9.horizontalSpan, l2));
					int j26 = Math.max(1, griddata9.verticalSpan);
					int k27 = 0;
					int l28 = 0;
					for (int i30 = 0; i30 < j25; i30++)
						k27 += ai[l20 + i30];

					for (int j30 = 0; j30 < j26; j30++)
						l28 += ai2[i16 + j30];

					k27 += horizontalSpacing * (j25 - 1);
					int k30 = j18 + griddata9.horizontalIndent;
					int k31 = Math.min(griddata9.cacheWidth, k27);
					switch (griddata9.horizontalAlignment) {
					case 2: // '\002'
					case 16777216:
						k30 += Math.max(0,
								(k27 - griddata9.horizontalIndent - k31) / 2);
						break;

					case 3: // '\003'
					case 131072:
					case 16777224:
						k30 += Math.max(0, k27 - griddata9.horizontalIndent
								- k31);
						break;

					case 4: // '\004'
						k31 = k27 - griddata9.horizontalIndent;
						break;
					}
					l28 += verticalSpacing * (j26 - 1);
					int l32 = j13 + griddata9.verticalIndent;
					int j33 = Math.min(griddata9.cacheHeight, l28);
					switch (griddata9.verticalAlignment) {
					case 2: // '\002'
					case 16777216:
						l32 += Math.max(0,
								(l28 - griddata9.verticalIndent - j33) / 2);
						break;

					case 3: // '\003'
					case 1024:
					case 16777224:
						l32 += Math
								.max(0, l28 - griddata9.verticalIndent - j33);
						break;

					case 4: // '\004'
						j33 = l28 - griddata9.verticalIndent;
						break;
					}
					ZComponent control4 = acontrol1[i16][l20];
					if (control4 != null)
						control4.setBounds(new ZRect(k30, l32, k31, j33));
				}
				j18 += ai[l20] + horizontalSpacing;
			}

			j13 += ai2[i16] + verticalSpacing;
		}

		for (int k13 = 0; k13 < j7; k13++)
			agriddata[k13].cacheWidth = agriddata[k13].cacheHeight = -1;

		int l13 = 0;
		int j16 = 0;
		for (int k18 = 0; k18 < l2; k18++)
			l13 += ai[k18];

		for (int l18 = 0; l18 < j2; l18++)
			j16 += ai2[l18];

		l13 += horizontalSpacing * (l2 - 1);
		j16 += verticalSpacing * (j2 - 1);
		if(acontrol.size()>0){
			ZComponent lastControl = (ZComponent)acontrol.elementAt(acontrol.size()-1);
			ZRect rect = lastControl.getBounds();
			return new ZPoint(k,rect.x+rect.height);
			
		}
		return new ZPoint(k,l);;
	}

	String getName() {
		String s = getClass().getName();
		int i = s.lastIndexOf('.');
		if (i == -1)
			return s;
		else
			return s.substring(i + 1, s.length());
	}

	public String toString() {
		String s = getName() + " {";
		if (numColumns != 1)
			s = s + "numColumns=" + numColumns + " ";
		if (makeColumnsEqualWidth)
			s = s + "makeColumnsEqualWidth=" + makeColumnsEqualWidth + " ";
		if (horizontalSpacing != 0)
			s = s + "horizontalSpacing=" + horizontalSpacing + " ";
		if (verticalSpacing != 0)
			s = s + "verticalSpacing=" + verticalSpacing + " ";
		s = s.trim();
		s = s + "}";
		return s;
	}

	public int numColumns;
	public boolean makeColumnsEqualWidth;
	public int horizontalSpacing;
	public int verticalSpacing;
}