-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 24, 2017 at 10:57 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gynaecology`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor_table`
--

CREATE TABLE `doctor_table` (
  `Patient_id` varchar(200) NOT NULL,
  `doctor_name` varchar(200) NOT NULL,
  `date_of_examination1` varchar(10) NOT NULL,
  `updateStatus` text,
  `time_stamp` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_table`
--

INSERT INTO `doctor_table` (`Patient_id`, `doctor_name`, `date_of_examination1`, `updateStatus`, `time_stamp`) VALUES
('070417052501', 'is', '07/04/2017', 'No', '07-04-2017-05-25-16'),
('070417055030', 'bbxnz', '07/04/2017', 'No', '07-04-2017-05-50-47'),
('070417060310', 'fig', '07/04/2017', 'No', '07-04-2017-06-03-33'),
('070417061530', 'she', '07/04/2017', 'No', '07-04-2017-06-20-11'),
('070417070634', 'all\n', '07/04/2017', 'No', '07-04-2017-09-41-55'),
('070417070634', 'ICJ', '07/04/2017', 'No', '07-04-2017-07-06-53'),
('070417070848', 'ICJ', '07/04/2017', 'No', '07-04-2017-07-09-05'),
('080417052202', 'Prasad', '08/04/2017', 'No', '08-04-2017-05-24-00');

-- --------------------------------------------------------

--
-- Table structure for table `first_trimester`
--

CREATE TABLE `first_trimester` (
  `Patient_id` varchar(200) NOT NULL,
  `hypermesis` text,
  `burning_micturition` text,
  `bleeding_piv_spotting_piv` text,
  `radiation_exposure` text,
  `fever_with_rash` text,
  `folic_acid_intake` text,
  `other_drug_intake` text,
  `others1` text,
  `scan` text,
  `updateStatus` text,
  `time_stamp` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `first_trimester`
--

INSERT INTO `first_trimester` (`Patient_id`, `hypermesis`, `burning_micturition`, `bleeding_piv_spotting_piv`, `radiation_exposure`, `fever_with_rash`, `folic_acid_intake`, `other_drug_intake`, `others1`, `scan`, `updateStatus`, `time_stamp`) VALUES
('070417052501', 'No', 'Yes', 'Yes', 'Yes', 'No', 'No', 'No', 'No', 'hehsshe', 'No', '07-04-2017-05-25-44'),
('070417055030', 'No', 'No', 'No', 'No', 'No', 'No', 'No', 'No', 'hshs', 'No', '07-04-2017-05-51-14'),
('070417060310', 'No', 'No', 'No', 'No', 'No', 'No', 'No', 'No', 'eueie', 'No', '07-04-2017-06-04-12'),
('070417061530', 'No', 'No', 'No', 'No', 'No', 'No', 'No', 'No', 'the Feb', 'No', '07-04-2017-06-20-21'),
('080417052202', 'No', 'No', 'No', 'No', 'No', 'No', 'No', 'No', 'sh d', 'No', '08-04-2017-05-29-13');

-- --------------------------------------------------------

--
-- Table structure for table `general_information`
--

CREATE TABLE `general_information` (
  `Patient_id` varchar(200) NOT NULL,
  `name` text,
  `date_of_examination` text,
  `age` text,
  `wfe_of` text,
  `occupation` text,
  `address` text,
  `mobile_number` text,
  `landline_number` text,
  `updateStatus` text,
  `time_stamp` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `general_information`
--

INSERT INTO `general_information` (`Patient_id`, `name`, `date_of_examination`, `age`, `wfe_of`, `occupation`, `address`, `mobile_number`, `landline_number`, `updateStatus`, `time_stamp`) VALUES
('070417052501', 'hdjd', '07/04/2017', '12', 'gzbz', 'whhw', 'hshs\nhehe\nhshs', '9494700707', '94944079797', 'No', '07-04-2017-05-25-16'),
('070417055030', 'zjzn', '07/04/2017', '12', 'zhz', 'bssb', 'bsbs\n2bbe\nbebs', '6164997977', '94940487970', 'No', '07-04-2017-05-50-47'),
('070417060310', 'hxjdjd', '07/04/2017', '123', 'gs', 'ged', 'h3he\nhe he\n3hhe', '6264487979', '64649494844', 'No', '07-04-2017-06-03-33'),
('070417061530', 'week', '07/04/2017', '22', 'cc', 'en', 'tf\nEdward\nthe', '4890898708', '58958855888', 'No', '07-04-2017-06-20-11'),
('070417070634', 'bsns', '07/04/2017', '12', 'ah', 'xhjxx', 'shhs\nheej\nhsjs', '1575797707', '99779799999', 'No', '07-04-2017-07-06-53'),
('070417070848', 'hcjc', '07/04/2017', '23', 'zhjz', 'dhssj2', 'jssjs\ndhjss\nhejes', '4278794979', '64494797878', 'No', '07-04-2017-07-09-05'),
('080417052202', 'Lavanya', '08/04/2017', '29', 'Sudarshan', 'NA', 'ramakuppam\nxhhz\nxhxh', '9944556677', '08015378584', 'No', '08-04-2017-05-24-00');

-- --------------------------------------------------------

--
-- Table structure for table `general_physical_examination`
--

CREATE TABLE `general_physical_examination` (
  `Patient_id` varchar(200) NOT NULL,
  `height` text,
  `weight` text,
  `bmi_pre_pregnancy` text,
  `pr` text,
  `bp` text,
  `temp` text,
  `breasts` text,
  `nipples` text,
  `thyroid` text,
  `spine` text,
  `icterus` text,
  `pallor` text,
  `edema` text,
  `cyanosis` text,
  `clubbing` text,
  `lymphadenopathy` text,
  `updateStatus` text,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `general_physical_examination`
--

INSERT INTO `general_physical_examination` (`Patient_id`, `height`, `weight`, `bmi_pre_pregnancy`, `pr`, `bp`, `temp`, `breasts`, `nipples`, `thyroid`, `spine`, `icterus`, `pallor`, `edema`, `cyanosis`, `clubbing`, `lymphadenopathy`, `updateStatus`, `time_stamp`) VALUES
('070417052501', '23', '12', '88', '85', '88', 'Afebrile', 'hcuf', 'Normal', 't7x7t', 'Normal', 'R6,6r', 'Absent', 'Absent', 'Absent', 'Absent', 'Absent', 'No', '07-04-2017-05-26-45'),
('070417055030', '239', '59', '55', '78', '48', 'Afebrile', 'fuf', 'Normal', '6rzr', 'Normal', 'NOT SPECIFIED', '+++', 'Absent', 'Absent', 'Absent', 'Absent', 'No', '07-04-2017-05-52-06'),
('070417060310', '12', '5', '5', '5', '5', 'Afebrile', 'Normal', 'gehe', 'Normal', 'Normal', 'NOT SPECIFIED', 'Absent', 'Absent', 'Absent', 'Absent', 'Absent', 'No', '07-04-2017-06-05-00'),
('070417061530', '23', '557', '87', '8770', '55', 'Afebrile', 'g DSC', 'Normal', 'Normal', 'Feb', 'NOT SPECIFIED', 'Absent', 'Absent', 'Absent', 'Absent', 'Absent', 'No', '07-04-2017-06-20-34'),
('070417070634', '12', '677', '64', '6', '6467', 'Afebrile', 'Normal', 'Normal', 'Normal', 'Normal', 'NOT SPECIFIED', 'Absent', 'Absent', 'Absent', 'Absent', 'Absent', 'No', '07-04-2017-07-08-22'),
('070417070848', '123', '8', '5', '88', '84', 'Febrile', 'gsbs', 'Normal', 'Normal', 'Normal', 'NOT SPECIFIED', '+++', 'Absent', 'gshs', 'Absent', 'ehhs', 'No', '07-04-2017-07-10-44'),
('080417052202', '156', '48', '24', '82', '9460', 'Afebrile', 'Normal', 'Normal', 'Normal', 'Normal', 'NOT SPECIFIED', 'Absent', 'Absent', 'Absent', 'Absent', 'Absent', 'No', '08-04-2017-05-42-18');

-- --------------------------------------------------------

--
-- Table structure for table `gynaec_care`
--

CREATE TABLE `gynaec_care` (
  `Patient_id` varchar(200) NOT NULL,
  `plan_of_care` text NOT NULL,
  `gynaec_advice` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gynaec_complaints`
--

CREATE TABLE `gynaec_complaints` (
  `Patient_id` varchar(200) NOT NULL,
  `gynaec_chief_complaints` text NOT NULL,
  `gynaec_lmp` text NOT NULL,
  `gynaec_history_of_present_illness` text NOT NULL,
  `gynaec_menstrual_history` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gynaec_family_history`
--

CREATE TABLE `gynaec_family_history` (
  `Patient_id` varchar(200) NOT NULL,
  `gynaec_diabetes2` text NOT NULL,
  `gynaec_hypertension2` text NOT NULL,
  `gynaec_tb2` text NOT NULL,
  `gynaec_malignancy` text NOT NULL,
  `gynaec_others6` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gynaec_investigations`
--

CREATE TABLE `gynaec_investigations` (
  `Patient_id` varchar(200) NOT NULL,
  `gynaec_hb` text NOT NULL,
  `gynaec_tc` text NOT NULL,
  `gynaec_platelet_count` text NOT NULL,
  `gynaec_peripheral_smear` text NOT NULL,
  `gynaec_blood_grouping_rh_typing` text NOT NULL,
  `gynaec_albumin` text NOT NULL,
  `gynaec_sugar` text NOT NULL,
  `gynaec_microscopy` text NOT NULL,
  `gynaec_cs` text NOT NULL,
  `gynaec_hiv` text NOT NULL,
  `gynaec_hbsag` text NOT NULL,
  `gynaec_vdrl` text NOT NULL,
  `gynaec_rbs` text NOT NULL,
  `gynaec_fbs` text NOT NULL,
  `gynaec_ppbs` text NOT NULL,
  `gynaec_ultra_sound` text NOT NULL,
  `gynaec_pap_smear` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gynaec_obstetric_history`
--

CREATE TABLE `gynaec_obstetric_history` (
  `Patient_id` varchar(200) NOT NULL,
  `gynaec_married_life` text NOT NULL,
  `gynaec_p` text NOT NULL,
  `gynaec_a` text NOT NULL,
  `gynaec_l` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gynaec_past_history`
--

CREATE TABLE `gynaec_past_history` (
  `Patient_id` varchar(200) NOT NULL,
  `gynaec_diabetes1` text NOT NULL,
  `gynaec_hypertension1` text NOT NULL,
  `gynaec_tb1` text NOT NULL,
  `gynaec_epilepsy` text NOT NULL,
  `gynaec_cardiac_disease` text NOT NULL,
  `gynaec_renal_disease` text NOT NULL,
  `gynaec_veneral_disease` text NOT NULL,
  `gynaec_blood_transfusion` text NOT NULL,
  `gynaec_past_surgeries` text NOT NULL,
  `gynaec_others5` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gynaec_personal_history`
--

CREATE TABLE `gynaec_personal_history` (
  `Patient_id` varchar(200) NOT NULL,
  `gynaec_diet` text NOT NULL,
  `gynaec_appetite` text NOT NULL,
  `gynaec_sleep` text NOT NULL,
  `gynaec_micturition` text NOT NULL,
  `gynaec_bowel` text NOT NULL,
  `gynaec_habits` text NOT NULL,
  `gynaec_allergy_history` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gynaec_physical_examination`
--

CREATE TABLE `gynaec_physical_examination` (
  `Patient_id` varchar(200) NOT NULL,
  `gynaec_height` double NOT NULL,
  `gynaec_weight` text NOT NULL,
  `gynaec_bmi` text NOT NULL,
  `gynaec_temp` text NOT NULL,
  `gynaec_pr` text NOT NULL,
  `gynaec_bp` text NOT NULL,
  `gynaec_thyroid` text NOT NULL,
  `gynaec_breasts` text NOT NULL,
  `gynaec_spine` text NOT NULL,
  `gynaec_pallor` text NOT NULL,
  `gynaec_icterus` text NOT NULL,
  `gynaec_cyanosis` text NOT NULL,
  `gynaec_clubbing` text NOT NULL,
  `gynaec_lymphadenopathy` text NOT NULL,
  `gynaec_edema` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gynaec_systemic_examination`
--

CREATE TABLE `gynaec_systemic_examination` (
  `Patient_id` varchar(200) NOT NULL,
  `gynaec_cvs` text NOT NULL,
  `gynaec_rs` text NOT NULL,
  `gynaec_cns` text NOT NULL,
  `gynaec_inspection` text NOT NULL,
  `gynaec_palpation` text NOT NULL,
  `gynaec_percussion` text NOT NULL,
  `gynaec_auscultation` text NOT NULL,
  `gynaec_local_examination` text NOT NULL,
  `gynaec_speculum_examination` text NOT NULL,
  `gynaec_bimanual_examination` text NOT NULL,
  `gynaec_rectal_examination` text NOT NULL,
  `gynaec_provisional_diagnosis` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `history_table`
--

CREATE TABLE `history_table` (
  `Patient_id` varchar(200) NOT NULL,
  `menstrual_history` text NOT NULL,
  `contraceptive_history` text NOT NULL,
  `past_history` text NOT NULL,
  `family_history` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `history_table`
--

INSERT INTO `history_table` (`Patient_id`, `menstrual_history`, `contraceptive_history`, `past_history`, `family_history`, `updateStatus`, `time_stamp`) VALUES
('070417052501', 'hshss', 'Insignificant', 'Insignificant', 'Insignificant', 'No', '07-04-2017-05-26-17'),
('070417055030', 'Regular', 'Insignificant', 'Insignificant', 'Insignificant', 'No', '07-04-2017-05-51-41'),
('070417060310', 'hshs', 'Insignificant', 'Insignificant', 'Insignificant', 'No', '07-04-2017-06-04-40'),
('070417061530', 'ab x', 'xdd', 'Insignificant', 'Insignificant', 'No', '07-04-2017-06-20-29'),
('070417070634', 'Regular', 'Insignificant', 'Insignificant', 'Insignificant', 'No', '07-04-2017-07-07-58'),
('070417070848', 'Regular', 'Insignificant', 'Insignificant', 'Insignificant', 'No', '07-04-2017-07-10-19'),
('080417052202', 'Regular', 'Insignificant', 'Insignificant', 'Insignificant', 'No', '08-04-2017-05-36-26');

-- --------------------------------------------------------

--
-- Table structure for table `investigations`
--

CREATE TABLE `investigations` (
  `Patient_id` varchar(200) NOT NULL,
  `trimester` varchar(200) NOT NULL,
  `hb` text,
  `blood_grouping_rh_typing` text,
  `albumin` text,
  `sugar` text,
  `microscopy` text,
  `vdrl` text NOT NULL,
  `hiv_status` text NOT NULL,
  `hbsag` text,
  `rbs` text,
  `others4` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `investigations`
--

INSERT INTO `investigations` (`Patient_id`, `trimester`, `hb`, `blood_grouping_rh_typing`, `albumin`, `sugar`, `microscopy`, `vdrl`, `hiv_status`, `hbsag`, `rbs`, `others4`, `updateStatus`, `time_stamp`) VALUES
('070417052501', 'Trimester-1', '2', 'B+', 'Normal', 'Abnormal', 'Normal', 'Non Reactive', 'Reactive', 'Non Reactive', 'wwhhwh', 'hehww', 'No', '07-04-2017-05-25-44'),
('070417055030', 'Trimester-1', '23', 'B-', 'Abnormal', 'Abnormal', 'Normal', 'Reactive', 'Non Reactive', 'Reactive', 'ywhs', 'hehw', 'No', '07-04-2017-05-51-14'),
('070417060310', 'Trimester-1', '23', 'A-', 'Abnormal', 'Normal', 'Abnormal', 'Non Reactive', 'Reactive', 'Non Reactive', 'ehe', 'yeue', 'No', '07-04-2017-06-04-12'),
('070417061530', 'Trimester-1', '23', 'B+', 'Normal', 'Abnormal', 'Abnormal', 'Non Reactive', 'Non Reactive', 'Reactive', 'xv Feb', 'Feb', 'No', '07-04-2017-06-20-21'),
('070417070634', 'Trimester-3', '23', 'A-', 'Abnormal', 'Normal', 'Normal', 'Abnormal', 'Reactive', 'Reactive', 'hssbs', 'hsjsjs', 'No', '07-04-2017-07-07-26'),
('070417070848', 'Trimester-2', '23', 'B+', '', 'Normal', '', 'Reactive', '', '', 'hzz', 'thZhz', 'No', '07-04-2017-07-09-38'),
('080417052202', 'Trimester-1', '12', 'A+', 'Normal', 'Abnormal', 'Normal', 'Non Reactive', 'Non Reactive', 'Non Reactive', 'No comments', 'djd', 'No', '08-04-2017-05-29-13');

-- --------------------------------------------------------

--
-- Table structure for table `obstetric_information`
--

CREATE TABLE `obstetric_information` (
  `Patient_id` varchar(200) NOT NULL,
  `history_of_amenorrhea_months` text,
  `history_of_amenorrhea_days` text,
  `lmp` text NOT NULL,
  `edd` text NOT NULL,
  `gestational_age_weeks` text,
  `corrected_edd` text NOT NULL,
  `any_complaints1` text,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `obstetric_information`
--

INSERT INTO `obstetric_information` (`Patient_id`, `history_of_amenorrhea_months`, `history_of_amenorrhea_days`, `lmp`, `edd`, `gestational_age_weeks`, `corrected_edd`, `any_complaints1`, `updateStatus`, `time_stamp`) VALUES
('070417052501', '2', '3', 'Not Specified', 'Not Specified', 'Not Specified', '9/4/2017', 'ahhss', 'No', '07-04-2017-05-25-26'),
('070417055030', '2', '3', 'Not Specified', 'Not Specified', 'Not Specified', '7/4/2017', 'zbbz', 'No', '07-04-2017-05-50-58'),
('070417060310', '2', '3', 'Not Specified', 'Not Specified', 'Not Specified', '9/4/2017', 'fig', 'No', '07-04-2017-06-03-54'),
('070417061530', '2', '3', 'Not Specified', 'Not Specified', 'Not Specified', '7/4/2017', 'carb', 'No', '07-04-2017-06-20-16'),
('070417070634', '2', '3', 'Not Specified', 'Not Specified', 'Not Specified', '7/4/2017', 'zbbzbz', 'No', '07-04-2017-07-07-04'),
('070417070848', '2', '8', 'Not Specified', 'Not Specified', 'Not Specified', '7/4/2017', 'sbbzjz', 'No', '07-04-2017-07-09-19'),
('080417052202', '2', '0', '25/11/2017', '18/2/2017', '5', '6/3/2017', 'No complaints', 'No', '08-04-2017-05-27-08');

-- --------------------------------------------------------

--
-- Table structure for table `obstetric_score`
--

CREATE TABLE `obstetric_score` (
  `Patient_id` varchar(200) NOT NULL,
  `g` text,
  `p` text,
  `a` text,
  `l` text,
  `d` text,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `obstetric_score`
--

INSERT INTO `obstetric_score` (`Patient_id`, `g`, `p`, `a`, `l`, `d`, `updateStatus`, `time_stamp`) VALUES
('070417052501', '2', '8', '8', '6', '8', 'No', '07-04-2017-05-26-02'),
('070417055030', '2', '33', '9', '8', '8', 'No', '07-04-2017-05-51-30'),
('070417060310', '5', '5', '5', '5', '5', 'No', '07-04-2017-06-04-27'),
('070417061530', '5', '5', '5', '565', '9', 'No', '07-04-2017-06-20-24'),
('070417070634', '2', '3', '8', '8', '9', 'No', '07-04-2017-07-07-44'),
('070417070848', '2', '5', '6', '8', '3', 'No', '07-04-2017-07-09-57'),
('080417052202', '2', '1', '0', '1', '0', 'No', '08-04-2017-05-32-43');

-- --------------------------------------------------------

--
-- Table structure for table `past_obstetric_history`
--

CREATE TABLE `past_obstetric_history` (
  `Patient_id` varchar(200) NOT NULL,
  `pregnancy_order` varchar(2) NOT NULL,
  `term_preterm` text NOT NULL,
  `mode_of_delivery` text NOT NULL,
  `antenatal` text NOT NULL,
  `intrapartum` text NOT NULL,
  `postpartum` text NOT NULL,
  `alive_dead` text NOT NULL,
  `gender` text NOT NULL,
  `baby_weight` text NOT NULL,
  `present_age_months` text NOT NULL,
  `present_age_days` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `past_obstetric_history`
--

INSERT INTO `past_obstetric_history` (`Patient_id`, `pregnancy_order`, `term_preterm`, `mode_of_delivery`, `antenatal`, `intrapartum`, `postpartum`, `alive_dead`, `gender`, `baby_weight`, `present_age_months`, `present_age_days`, `updateStatus`, `time_stamp`) VALUES
('070417052501', '1', 'hshssv', 'Vaginal', 'Not Specified', 'Not Specified', 'Not Specified', 'Alive', 'Female', '8', '8', '9', 'No', '07-04-2017-05-26-02'),
('070417055030', '1', 'vs', 'Operative Vaginal', 'Not Specified', 'Yes', 'Not Specified', 'Dead', 'Female', '6', '6', '9', 'No', '07-04-2017-05-51-30'),
('070417060310', '1', 'gshs', 'Operative Vaginal', 'Not Specified', 'Not Specified', 'Not Specified', 'Alive', 'Female', '56', '5', '5', 'No', '07-04-2017-06-04-27'),
('070417061530', '1', 'f', 'Cesarean', 'Not Specified', 'Not Specified', 'Not Specified', 'Alive', 'Male', '55', '5', '5', 'No', '07-04-2017-06-20-24'),
('070417070634', '1', 'zhhbzsbsbsb', 'Operative Vaginal', 'Not Specified', 'Not Specified', 'Not Specified', 'Alive', 'Female', '12', '5', '3', 'No', '07-04-2017-07-07-44'),
('070417070848', '1', 'bsbsbb', 'Operative Vaginal', 'Not Specified', 'Not Specified', 'Not Specified', 'Dead', 'Male', '56', '8', '8', 'No', '07-04-2017-07-09-59'),
('070417070848', '2', 'sh ss', 'Operative Vaginal', 'Not Specified', 'Not Specified', 'Not Specified', 'Alive', 'Female', '8', '5', '8', 'No', '07-04-2017-07-09-57'),
('080417052202', '1', '1', 'Operative Vaginal', 'Not Specified', 'Not Specified', 'Not Specified', 'Alive', 'Male', '3', '12', '3', 'No', '08-04-2017-05-32-43');

-- --------------------------------------------------------

--
-- Table structure for table `personal_history`
--

CREATE TABLE `personal_history` (
  `Patient_id` varchar(200) NOT NULL,
  `diet` text,
  `sleep` text,
  `addictive_habits` text,
  `allergies_known` text,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personal_history`
--

INSERT INTO `personal_history` (`Patient_id`, `diet`, `sleep`, `addictive_habits`, `allergies_known`, `updateStatus`, `time_stamp`) VALUES
('070417052501', 'Mixed', 'Disturbed', 'No', 'No', 'No', '07-04-2017-05-26-17'),
('070417055030', 'Mixed', 'Disturbed', 'No', 'No', 'No', '07-04-2017-05-51-41'),
('070417060310', 'Mixed', 'Normal', 'No', 'No', 'No', '07-04-2017-06-04-40'),
('070417061530', 'Mixed', 'Disturbed', 'No', 'No', 'No', '07-04-2017-06-20-29'),
('070417070634', 'Mixed', 'Normal', 'No', 'No', 'No', '07-04-2017-07-07-58'),
('070417070848', 'Veg', 'Disturbed', 'No', 'No', 'No', '07-04-2017-07-10-19'),
('080417052202', 'Veg', 'Normal', 'No', 'No', 'No', '08-04-2017-05-36-26');

-- --------------------------------------------------------

--
-- Table structure for table `second_trimester`
--

CREATE TABLE `second_trimester` (
  `Patient_id` varchar(200) NOT NULL,
  `quickening` text,
  `history_of_bleeding_piv` text,
  `leak_piv1` text NOT NULL,
  `pih_history1` text NOT NULL,
  `tt` text NOT NULL,
  `iron_tablets1` text NOT NULL,
  `calcium_tablets1` text NOT NULL,
  `others2` text NOT NULL,
  `anomaly_scan` text NOT NULL,
  `updateStatus` text NOT NULL,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `second_trimester`
--

INSERT INTO `second_trimester` (`Patient_id`, `quickening`, `history_of_bleeding_piv`, `leak_piv1`, `pih_history1`, `tt`, `iron_tablets1`, `calcium_tablets1`, `others2`, `anomaly_scan`, `updateStatus`, `time_stamp`) VALUES
('070417070848', 'No', 'No', 'No', 'No', 'No', 'No', 'No', 'No', 'vvnkb', 'No', '07-04-2017-07-09-38');

-- --------------------------------------------------------

--
-- Table structure for table `systemic_examination`
--

CREATE TABLE `systemic_examination` (
  `Patient_id` varchar(200) NOT NULL,
  `cvs` text,
  `rs` text,
  `cns` text,
  `uterine_size` text,
  `presentation` text NOT NULL,
  `fhs` text,
  `liqour` text,
  `previous_scar` text,
  `updateStatus` text,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `systemic_examination`
--

INSERT INTO `systemic_examination` (`Patient_id`, `cvs`, `rs`, `cns`, `uterine_size`, `presentation`, `fhs`, `liqour`, `previous_scar`, `updateStatus`, `time_stamp`) VALUES
('070417052501', 'Normal', 'Normal', 'Normal', '050', 'Cephalic', '+++', 'Reduced', 'Absent', 'No', '07-04-2017-05-26-57'),
('070417055030', 'ufr7x', 'Normal', 'Normal', '27', 'Transverse', 'Absent', 'Reduced', 'Absent', 'No', '07-04-2017-05-52-17'),
('070417060310', 'dhjs', 'Normal', 'Normal', '560', 'Breech', 'Absent', 'Excess', 'Absent', 'No', '07-04-2017-06-05-13'),
('070417061530', 'Normal', 'Normal', 'Normal', '23', 'Breech', 'Absent', 'Reduced', 'Absent', 'No', '07-04-2017-06-20-37'),
('070417070634', 'Normal', 'Normal', 'Normal', '6', 'Transverse', '++', 'Reduced', 'Absent', 'No', '07-04-2017-07-08-37'),
('070417070848', 'Normal', 'Normal', 'Normal', '44', 'Cephalic', 'Absent', 'Adequate', 'zhhzz', 'No', '07-04-2017-07-10-59'),
('080417052202', 'Normal', 'Normal', 'Normal', '8', 'Breech', '++', 'Adequate', 'Absent', 'No', '08-04-2017-05-46-23');

-- --------------------------------------------------------

--
-- Table structure for table `third_trimester`
--

CREATE TABLE `third_trimester` (
  `Patient_id` varchar(200) NOT NULL,
  `pain_in_abdomen` text,
  `leak_piv2` text,
  `bleeding_piv` text,
  `foetal_movements` text,
  `pih_history2` text,
  `iron_tablets2` text,
  `calcium_tablets2` text,
  `others3` text,
  `growth_scan` text,
  `updateStatus` text,
  `time_stamp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `third_trimester`
--

INSERT INTO `third_trimester` (`Patient_id`, `pain_in_abdomen`, `leak_piv2`, `bleeding_piv`, `foetal_movements`, `pih_history2`, `iron_tablets2`, `calcium_tablets2`, `others3`, `growth_scan`, `updateStatus`, `time_stamp`) VALUES
('070417070634', 'No', 'Yes', 'Yes', 'Yes', 'No', 'No', 'Yes', 'No', 'ejjsjs', 'No', '07-04-2017-07-07-26');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor_table`
--
ALTER TABLE `doctor_table`
  ADD PRIMARY KEY (`Patient_id`,`doctor_name`,`date_of_examination1`);

--
-- Indexes for table `first_trimester`
--
ALTER TABLE `first_trimester`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `general_information`
--
ALTER TABLE `general_information`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `general_physical_examination`
--
ALTER TABLE `general_physical_examination`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `gynaec_care`
--
ALTER TABLE `gynaec_care`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `gynaec_complaints`
--
ALTER TABLE `gynaec_complaints`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `gynaec_family_history`
--
ALTER TABLE `gynaec_family_history`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `gynaec_investigations`
--
ALTER TABLE `gynaec_investigations`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `gynaec_obstetric_history`
--
ALTER TABLE `gynaec_obstetric_history`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `gynaec_past_history`
--
ALTER TABLE `gynaec_past_history`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `gynaec_personal_history`
--
ALTER TABLE `gynaec_personal_history`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `gynaec_physical_examination`
--
ALTER TABLE `gynaec_physical_examination`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `gynaec_systemic_examination`
--
ALTER TABLE `gynaec_systemic_examination`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `history_table`
--
ALTER TABLE `history_table`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `investigations`
--
ALTER TABLE `investigations`
  ADD PRIMARY KEY (`Patient_id`,`trimester`);

--
-- Indexes for table `obstetric_information`
--
ALTER TABLE `obstetric_information`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `obstetric_score`
--
ALTER TABLE `obstetric_score`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `past_obstetric_history`
--
ALTER TABLE `past_obstetric_history`
  ADD PRIMARY KEY (`Patient_id`,`pregnancy_order`);

--
-- Indexes for table `personal_history`
--
ALTER TABLE `personal_history`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `second_trimester`
--
ALTER TABLE `second_trimester`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `systemic_examination`
--
ALTER TABLE `systemic_examination`
  ADD PRIMARY KEY (`Patient_id`);

--
-- Indexes for table `third_trimester`
--
ALTER TABLE `third_trimester`
  ADD PRIMARY KEY (`Patient_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
